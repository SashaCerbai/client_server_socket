package com.example;
import java.io.*;
import java.net.*;
public class Server_socket {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione ...");
            //creo un server sulla porta 6789
            server = new ServerSocket(6789);
            //rimane in attesa di un cliente
            client = server.accept();
            //chiudo il server per inibire altri client
            server.close();
            //associo due oggetti al socket del client per effettuare la scittura e la lettura
            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try{
            //rimango in attesa della riga tramessa dal client
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6 ricevuta la stringa dal cliente : " + stringaRicevuta);

            //la modifico e la rispedisco al client
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 ivio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata+'\n');

            //termina elaborazione sul server : chiudo la connesione del client
            System.out.println("9 SERVER: fine elaborazione ... buona notte!");
            client.close();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il client!");
            System.exit(1);
        }
    }
}
