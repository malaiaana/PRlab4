package com.lab4;

import java.io.IOException;

public class ClientInput implements Runnable {

    @Override
    public void run() {
        while (Client.connected){
            try {
                String msg = Client.input.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                Client.connected = false;
                Client.quit();
                System.out.println("подключение разорвано");
            }
        }
    }
}
