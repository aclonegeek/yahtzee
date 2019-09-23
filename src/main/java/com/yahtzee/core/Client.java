// Reference: https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html

package com.yahtzee.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Specify an IP and port number");
            System.exit(-1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        Client client = new Client();
        client.start(host, port);
    }

    public void start(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Failed to connect to " + host);
            System.exit(-1);
        }

        try {
            // Client-to-server.
            new ClientThread(System.in, this.socket.getOutputStream()).start();
            // Server-to-client.
            new ClientThread(this.socket.getInputStream(), System.out).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ClientThread extends Thread {
        private final InputStream in;
        private final OutputStream out;

        public ClientThread(InputStream in, OutputStream out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(this.in));
                 PrintWriter out = new PrintWriter(this.out, true)) {
                while (true) {
                    String message = in.readLine();
                    if (message != null) {
                        out.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
