package Servidor;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mainServidor {
    public static void main(String[] args) {
        int puerto = 1234;
        String palabraClave = "adios";
        int maxClients = 2; // Argumento para el número máximo de clientes.

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Iniciando servidor... OK");

            ExecutorService pool = Executors.newFixedThreadPool(maxClients);

            for (int i = 1; i <= maxClients; i++) {
                Socket socket = serverSocket.accept();
                System.out.println("Conexión desde el cliente " + i + "... OK");

                Runnable r = new Handler(socket, i, palabraClave);
                pool.execute(r);
            }

            pool.shutdown(); // No se aceptarán más clientes.

            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } 
    }
}
