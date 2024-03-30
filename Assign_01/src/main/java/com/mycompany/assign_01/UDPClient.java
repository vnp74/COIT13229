package com.mycompany.assign_01;

import java.io.*;
import java.net.*;

import javax.sound.sampled.Port;

public class UDPClient {
    private static final int server_port = 2225;
    private static final String server_ip = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {

            InetAddress serverAddress = InetAddress.getByName(server_ip);

            byte[] sendBuffer = "Ping".getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, server_port);
            socket.send(sendPacket);

            System.out.println("Ping message sent to server.");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
