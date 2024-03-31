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

                // Validate inputs before sending
                String namePattern = "^[A-Za-z-']+$";
                String addressPattern = "^.+$"; // Basic check for non-empty
                String phoneNumberPattern = "\\+?[0-9]{10,13}"; // expression to validate phone number

                // Validate First Name
                if (!firstName.matches(namePattern)) {
                    System.out.println("Invalid First Name. Please enter a valid First Name.");
                    continue; // Skip sending to server and prompt for details again
                }

                // Validate Last Name
                if (!lastName.matches(namePattern)) {
                    System.out.println("Invalid Last Name. Please enter a valid Last Name.");
                    continue; // Skip sending to server and prompt for details again
                }

                // Validate Address
                if (!address.matches(addressPattern)) {
                    System.out.println("Invalid Address. Please enter a valid Address.");
                    continue; // Skip sending to server and prompt for details again
                }

                // Validate Phone Number
                if (!phoneNumber.matches(phoneNumberPattern)) {
                    System.out.println("Invalid Phone Number. Please enter a valid Phone Number.");
                    continue; // Skip sending to server and prompt for details again
                }

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
        }

        catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}
