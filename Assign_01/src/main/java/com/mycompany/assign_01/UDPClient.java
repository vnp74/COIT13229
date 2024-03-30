package com.mycompany.assign_01;

import java.io.*;
import java.net.*;

public class UDPClient {
    private static final int server_port = 2225;
    private static final String server_ip = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {

            InetAddress serverAddress = InetAddress.getByName(server_ip);

            byte[] sendBuffer = "memberlistobject".getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, server_port);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            socket.receive(receivePacket);

            String receiveData = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Server Response: ");
            System.out.println(receiveData.trim());
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
