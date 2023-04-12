package Servidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class mainServidor {

	public static void main(String[] args) {
        int puerto = 1234;
        String palabraClave = "adios";

        try {
        	ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Iniciando servidor... OK");

            Socket socket = serverSocket.accept();
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String mensaje;
            boolean continuar = true;

            while (continuar) {
                mensaje = entrada.readLine();
                if (mensaje.equalsIgnoreCase(palabraClave)) {
                    continuar = false;
                } else {
                    System.out.println("Cliente: " + mensaje);

                    System.out.print("Servidor: ");
                    mensaje = scanner.nextLine();
                    salida.println(mensaje);
                    if (mensaje.equalsIgnoreCase(palabraClave)) {
                        continuar = false;
                    }
                }
            }
            System.out.println("Cerrando servidor... OK");
            
            scanner.close();
            entrada.close();
            salida.close();
            socket.close();
            serverSocket.close();
            
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } 
        
    }
	
}
