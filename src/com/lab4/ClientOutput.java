package com.lab4;

import java.io.IOException;
import java.util.Scanner;

public class ClientOutput implements Runnable {

    Scanner scanner = new Scanner(System.in);
    @Override
    public void run() {
        while (Client.connected){
            String msg = scanner.nextLine();
            try {
                Client.output.writeUTF(msg);
            } catch (IOException e) {
                Client.connected = false;
                Client.quit();
            }
        }
    }
}
