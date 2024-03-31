package com.mycompany.assign_01;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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

                if ("memberlistObject".equalsIgnoreCase(receivedText.trim())) {
                    String memberDetails = deserializeMemberList();
                    byte[] sendBuffer = memberDetails.getBytes();

                    InetAddress clientAddress = receivePacket.getAddress();

                    int clientPort = receivePacket.getPort();

                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress,
                            clientPort);
                    socket.send(sendPacket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String deserializeMemberList() {
        StringBuilder memberDetails = new StringBuilder();
        memberDetails.append("|First Name   |Last Name  |Address             |Phone Number   |\n");
        memberDetails.append("|=============|===========|====================|===============|\n");
        File file = new File("memberlistObject");
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("memberlistObject"))) {
            @SuppressWarnings("unchecked")
            ArrayList<member> members = (ArrayList<member>) ois.readObject();

            for (member member : members) {
                memberDetails.append(member.toString()).append("\n");
            }
        } catch (FileNotFoundException e) {
            return "Error: File not found - " + e.getMessage();
        } catch (IOException e) {
            return "Error reading from file - " + e.getMessage();
        } catch (ClassNotFoundException e) {
            return "Class not found during deserialization - " + e.getMessage();
        } catch (ClassCastException e) {
            return "Class cast problem during deserialization - " + e.getMessage();
        }
        return memberDetails.toString();
    }
}
