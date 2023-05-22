package Servidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Handler implements Runnable {
    private Socket clientSocket;
    private int clientNumber;
    private String serverKeyword;

    public Handler(Socket clientSocket, int clientNumber, String serverKeyword) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
        this.serverKeyword = serverKeyword;
    }

    public void run() {
        try {
            PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String mensaje;
            boolean continuar = true;

            while (continuar) {
                mensaje = entrada.readLine();
                if (mensaje.equalsIgnoreCase(serverKeyword)) {
                    continuar = false;
                } else {
                    System.out.println("Cliente " + clientNumber + ": " + mensaje);

                    System.out.print("Servidor: ");
                    mensaje = scanner.nextLine();
                    salida.println(mensaje);
                    if (mensaje.equalsIgnoreCase(serverKeyword)) {
                        continuar = false;
                    }
                }
            }

            scanner.close();
            entrada.close();
            salida.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente " + clientNumber + ": " + e.getMessage());
        }
    }
}

