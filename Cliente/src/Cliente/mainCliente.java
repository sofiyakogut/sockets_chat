package Cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class mainCliente {
	
	
	 public static void main(String[] args) {
	        String host = "localhost";
	        int puerto = 1234;
	        String palabraClave = "chao";

	        try (Socket socket = new Socket(host, puerto);
	             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
	             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	             Scanner scanner = new Scanner(System.in)) {

	            System.out.println("Iniciando cliente... OK");

	            String mensaje;
	            while (true) {
	                System.out.print("Cliente: ");
	                mensaje = scanner.nextLine();
	                salida.println(mensaje);
	                if (mensaje.equalsIgnoreCase(palabraClave)) {
	                    break;
	                }

	                mensaje = entrada.readLine();
	                if (mensaje.equalsIgnoreCase(palabraClave)) {
	                    break;
	                }
	                System.out.println("Servidor: " + mensaje);
	            }
	            System.out.println("Cerrando cliente... OK");
	        } catch (IOException e) {
	            System.err.println("Error en el cliente: " + e.getMessage());
	        }
	    }

}
