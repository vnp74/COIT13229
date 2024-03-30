package com.mycompany.assign_01;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        // Server hostname and port configuration.
        
        String hostname = "localhost";
        int port = 1125; // Port number (1125 - 25 is the last two digits of my student id)

        try (Socket socket = new Socket(hostname, port)) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Please enter the following details for member registration:");

                // Prompting for first name.
                System.out.print("First Name: ");
                String firstName = scanner.nextLine();

                if ("exit".equalsIgnoreCase(firstName)) {
                    break; // Exit loop if user inputs 'exit'.
                }

                // Prompting for last name.
                System.out.print("Last Name: ");
                String lastName = scanner.nextLine();

                // Prompting for address.
                System.out.print("Address: ");
                String address = scanner.nextLine();

                // Prompting for phone number.
                System.out.print("Phone Number: ");
                String phoneNumber = scanner.nextLine();

                // Sending member details to the server.
                dos.writeUTF(firstName + "," + lastName + "," + address + "," + phoneNumber);

                // Awaiting and printing server's response.
                String response = dis.readUTF();
                System.out.println("Server response: " + response);

                // Asking if the user wants to continue or exit.
                System.out.println("Do you want to register another member? (yes/no)");
                String answer = scanner.nextLine();

                if ("no".equalsIgnoreCase(answer)) {
                    System.out.println("Exiting registration process.");
                    break;
                }
            }
        }

        catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
