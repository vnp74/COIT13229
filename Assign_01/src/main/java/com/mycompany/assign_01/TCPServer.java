
package com.mycompany.assign_01;

import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int Port = 1125; // Port number (1125 - 25 is the last two digits of my student id)

    public static void main(String[] args) {
        // server socket looking for clients trying to connect
        try (ServerSocket serverSocket = new ServerSocket(Port)) {
            System.out.println("Server is listening on port" + Port);
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
