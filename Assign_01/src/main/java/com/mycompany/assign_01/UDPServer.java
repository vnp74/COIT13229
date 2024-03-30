package com.mycompany.assign_01;

import java.io.*;
import java.net.*;

public class UDPServer {
    private static final int Port = 2225; // Server's listening port (student id - 12191825)

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(Port)) {
            System.out.println("UDP Server is running on Port: " + Port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
