package com.lab4;

import java.net.ServerSocket;
import java.util.ArrayList;

public class Context {
    public static ArrayList<UserManager> users = new ArrayList<>();
    public static boolean serverIsWorking = true;
    public static final int SERVER_PORT = 10000;
    public static  final String IP = "127.0.0.1";
}
