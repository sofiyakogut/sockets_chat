package Servidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class mainServidor {

	public static void main(String[] args) {
        int puerto = 1234;
        String palabraClave = "adios";

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Iniciando servidor... OK");

            try (Socket socket = serverSocket.accept();
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 Scanner scanner = new Scanner(System.in)) {

                String mensaje;
                while (true) {
                    mensaje = entrada.readLine();
                    if (mensaje.equalsIgnoreCase(palabraClave)) {
                        break;
                    }
                    System.out.println("Cliente: " + mensaje);

                    System.out.print("Servidor: ");
                    mensaje = scanner.nextLine();
                    salida.println(mensaje);
                    if (mensaje.equalsIgnoreCase(palabraClave)) {
                        break;
                    }
                }
            }
            System.out.println("Cerrando servidor... OK");
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
	
	
}
