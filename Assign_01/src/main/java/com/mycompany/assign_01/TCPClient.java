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
        }

        catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
