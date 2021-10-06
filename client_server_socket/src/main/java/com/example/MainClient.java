package com.example;
    
public class MainClient{

    public static void main( String[] args )
    {
        Client_socket cliente = new Client_socket();
        cliente.connetti();
        cliente.comunica();
        
    }
}

