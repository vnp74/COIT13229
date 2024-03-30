package com.mycompany.assign_01;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    private static final int Port = 1125; // Port number (1125 - 25 is the last two digits of my student id)
    private static int ClientCounter = 0; // number of clients

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool(); // Thread pool for handling multiple clients

        // Start the serialization timer task
        Timer timer = new Timer();
        timer.schedule(new SerializeMembersTask(), 0, 2000); // Schedule serialization every 2 seconds

        try (ServerSocket serverSocket = new ServerSocket(Port)) {
            System.out.println("Server is listening on port " + Port);

            while (true) {
                Socket socket = serverSocket.accept();
                synchronized (TCPServer.class) {
                    ClientCounter++; // Increment client count for each new connection
                }
                System.out.println("Client " + ClientCounter + " connected");
                executor.submit(new ClientHandler(socket, ClientCounter)); // Pass the client number to the handler
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private int clientNumber;

        public ClientHandler(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber; // This client's unique number
        }

        @Override
        public void run() {
            System.out.println("Receiving data from client: " + clientNumber);
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // Read member details sent by client
                String memberDetails = dis.readUTF();

                // Save the member details to a file (append mode)
                synchronized (ClientHandler.class) {
                    try (PrintWriter out = new PrintWriter(new FileOutputStream("memberlist.txt", true))) {
                        out.println(memberDetails);
                    }
                }

                // Send confirmation back to client
                dos.writeUTF("Member details received and saved.");

            } catch (IOException e) {
                System.err.println("IO Exception in client handler: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class SerializeMembersTask extends TimerTask {
        @Override
        public void run() {
            synchronized (SerializeMembersTask.class) {
                try {
                    List<String> memberLines = Files.readAllLines(Paths.get("memberlist.txt"));
                    List<member> members = new ArrayList<>();

                    for (String line : memberLines) {
                        String[] details = line.split(":");
                        if (details.length == 4) {
                            members.add(new member(details[0], details[1], details[2], details[3]));
                        }
                    }

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("memberlistObject"))) {
                        oos.writeObject(members); // Serialize the entire list at once
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
