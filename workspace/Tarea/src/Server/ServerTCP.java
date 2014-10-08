package Server;

import java.net.*;
import java.io.*;


public class ServerTCP extends Thread{

	//Parametros para el servidor de TCD
    // Numero de puerto debe ser mayor que 1024
    public static final int PORT = 9025;
  
    
  
	
	//contructor de la clase
		public ServerTCP(String str) {
	        super(str);
	    }
	
	
	////////////////////////////////////////////////
	// implementacion de las funciones de TCP 
	////////////////////////////////////////////////
//argumento_0 = 
//argumento_1 = 
//   public  void TCP(String args[]) 
    public  void run() {
    	
    	
    	
        ServerSocket sersock = null;
        Socket sock = null;
        System.out.println(" Espere !! ");
  

        try { 
        	//  Inicializando el ServerSocket
            sersock = new ServerSocket(PORT);

            // Dar los detalles de la m�quina y n�mero de puerto

            System.out.println("Servidor ha arrancado: " + sersock);

            // Hacer una conexi�n de socket a un cliente espec�fico
            // que permita comunicaci�n bidireccional

            for (;;) {
                System.out.println("Esperando conexion: " + sock);

                sock = sersock.accept();

                System.out.println("Cliente conectado: " + sock);

                // Recibir un mensaje de petici�n del cliente

                BufferedReader is = new BufferedReader(
                        new InputStreamReader(sock.getInputStream()));

                System.out.println(is.readLine());

                // Enviar al cliente un mensaje de respuesta
                PrintStream ios = new PrintStream(sock.getOutputStream());
                ios.println("Hola desde el servidor!");
                ios.close();

                // Cerrar la conexi�n del socket!

                sock.close();
            }
        } catch (SocketException se) {
            System.out.println("Problemas con socket del servidor " + se.getMessage());
        } catch (Exception e) {
            System.out.println("No pudo arrancar " + e.getMessage());
        }

        System.out.println(" Conexion desde :  " + sock.getInetAddress());

    } // main
    
    
    
    
}  // Clase Server