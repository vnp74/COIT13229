package com.mycompany.assign_01;

import java.io.*;
import java.net.*;

public class UDPServer {
    private static final int Port = 2225; // Server's listening port (student id - 12191825)

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(Port)) {
            System.out.println("UDP Server is running on Port: " + Port);

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String receivedText = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received:" + receivedText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
