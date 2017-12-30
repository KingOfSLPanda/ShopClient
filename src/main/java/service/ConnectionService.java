package main.java.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by User on 16.11.2017.
 */
public class ConnectionService {
    private static Socket socket;
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    public static void connect() throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 8080);
        outputStream = new ObjectOutputStream(socket.getOutputStream()); //создание потока вывода
        inputStream = new ObjectInputStream(socket.getInputStream());   //создание потока ввода
    }


    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket sock) {
        socket = sock;
    }

    public static ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public static void setOutputStream(ObjectOutputStream oStream) {
        outputStream = oStream;
    }

    public static ObjectInputStream getInputStream() {
        return inputStream;
    }

    public static void setInputStream(ObjectInputStream iStream) {
        inputStream = iStream;
    }
}
