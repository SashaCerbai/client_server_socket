package com.example;

public class MainServer {

    public static void main( String[] args )
    {
        Server_socket server = new Server_socket();
        server.attendi();
        server.comunica();
    }
}
