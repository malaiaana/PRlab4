package com.lab4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserManager implements Runnable {

    private Socket user;
    private DataInputStream input;
    private DataOutputStream output;
    private String userName = "anonymous";
    private boolean connected = true;

    public UserManager(Socket user) throws IOException {

        this.user = user;
        input = new DataInputStream(user.getInputStream());
        output = new DataOutputStream(user.getOutputStream());
    }

    @Override
    public void run() {
        try {
            output.writeUTF("Какое ваше имя?");
            userName = input.readUTF();
            output.writeUTF("Чтобы покинуть чат, введите 'выход'");
            output.writeUTF("Чтобы выключить сервер, введите 'остановить'");
            String msg;
            while (connected) {
                msg = input.readUTF();
                if (msg.equals("выход")) {
                    quit();
                    say(userName + " покинул чат");
                    break;
                }
                if (msg.equals("остановить")){
                    Server.socket.close();
                }

                say(userName + ": " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getUser() {
        return user;
    }

    private void say(String msg) throws IOException {
        for (UserManager userManager : Context.users) {
            userManager.getOutput().writeUTF(msg);
        }
    }

    private void quit() {
        try {
            user.close();
        } catch (IOException e) {
            System.out.println("Ошибка");
        } finally {
            System.out.println("Закрыт сокет пользователя " + userName);
            connected = false;
            Context.users.remove(this);
        }
    }

    public DataOutputStream getOutput() {
        return output;
    }
}
