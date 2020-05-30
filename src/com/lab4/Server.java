package com.lab4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket socket;

    public static void main(String[] args) {
        try {
            socket = new ServerSocket(Context.SERVER_PORT);
            while (Context.serverIsWorking) {
                Socket user = socket.accept();
                UserManager userManager = new UserManager(user);
                Context.users.add(userManager);
                Thread userThread = new Thread(userManager);
                userThread.start();
            }
        } catch (IOException e) {
            Context.serverIsWorking = false;
            System.out.println("сервер выключается");
            for (UserManager userManager : Context.users) {
                try {
                    userManager.getUser().close();
                } catch (Exception exception) {
                    System.out.println("ошибка при отключении");
                }
            }
        }
    }


}
