package com.example;
import java.io.*;
import java.net.*;



public class Client_socket {
    String nomeServer = "localhost"; //nome
    int portaServer= 6789; //la porta
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalserver;
    BufferedReader inDalServer; //stream input
    DataOutputStream outVersoServer; //stream output

 
    public Socket connetti (){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try{
            //per l'imput da tastiera 
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //si crea un socket
            mioSocket = new Socket(nomeServer,portaServer);
            //miosocket = new Socket(InetAddress.getLocalHost(), 6789);
            //aasocio due oggetti al mio socket per effettuare la scittura e la lettura
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        }
        catch (UnknownHostException e){
            System.err.println("Host sconosciuto");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
     
        return mioSocket;        
    }


    public void comunica(){
        try
        {
            System.out.println("4 ... inserisci la stringa da trasmettere al server"+'\n');
            stringaUtente = tastiera.readLine();
            //spedisco al server
            System.out.println("5 ... invio la stringa al server e attendo ... ");
            outVersoServer.writeBytes ( stringaUtente+'\n');
            //leggo la risposta del server 
            stringaRicevutaDalserver=inDalServer.readLine();
            System.out.println("8 ... risposta del server"+ '\n' +stringaRicevutaDalserver);
            //chiudo connessione
            System.out.println("9 CLIENT: termina elaborazione e chiude connesione");
            mioSocket.close();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }

}
