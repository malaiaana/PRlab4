package com.lab4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static Socket socket;
    public static DataInputStream input;
    public static DataOutputStream output;
    public static boolean connected = false;
    private static Thread inputThread;
    private static Thread outputThread;

    public static void main(String[] args) {
        try {
            socket = new Socket(Context.IP, Context.SERVER_PORT);
            connected = true;
            ClientInput clientInput = new ClientInput();
            ClientOutput clientOutput = new ClientOutput();
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            inputThread = new Thread(clientInput);
            outputThread = new Thread(clientOutput);
            inputThread.start();
            outputThread.start();
        } catch (IOException e) {
            System.out.println("ошибка");
            inputThread.interrupt();
            outputThread.interrupt();
        }
    }

    public static void quit() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("ошибка");
            inputThread.interrupt();
            outputThread.interrupt();
        }
    }
}
