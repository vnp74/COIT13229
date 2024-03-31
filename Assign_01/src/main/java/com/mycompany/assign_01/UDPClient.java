package com.mycompany.assign_01;

import java.io.*;
import java.net.*;

public class UDPClient {
    private static final int server_port = 2225; // student id - 12191825
    private static final String server_ip = "localhost";

    public static void main(String[] args) {
        // Try-with-resources statement to ensure the socket is closed after use.
        try (DatagramSocket socket = new DatagramSocket()) {

            // Resolve the IP address of the server.
            InetAddress serverAddress = InetAddress.getByName(server_ip);

            byte[] sendBuffer = "memberlistobject".getBytes(); // converting memberlistObject into a byte array for sending

            // Creating a packet to send, specifying the data, the server's IP address, and port.
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, server_port);

            socket.send(sendPacket); // sending packet via socket

            byte[] receiveBuffer = new byte[4096]; // byte array to receive and hold the data

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length); // creating a packetto receive data into buffer                                                           
            socket.receive(receivePacket);

            String receiveData = new String(receivePacket.getData(), 0, receivePacket.getLength()); // Converting the received bytes into a string

            System.out.println("Server Response: ");
            System.out.println(receiveData.trim()); // removing white-spaces
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
