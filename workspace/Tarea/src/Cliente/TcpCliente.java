package Cliente;

import java.io.*;
import java.net.*;


class TcpCliente extends Thread{

	
	//contructor de la clase
	public TcpCliente(String str) {
        super(str);
    }
	
	
	
    public void  run() {
    	int PORT = 9025 ;
         Socket sock=null; DataInputStream dis=null; PrintStream ps=null;

         try { // obtener direcci�n IP del servidor por nombre
 
			System.out.println(" Tratando de conectarse!");
			InetAddress ip =InetAddress.getByName("localhost");

			// Conect�ndose a puerto 9025 declarado en clase TcpServer
			// Crear un socket al que se asocie el servidor.

            sock= new Socket(ip,PORT); ps= new 
            PrintStream(sock.getOutputStream());
			ps.println("Hola desde el cliente!");

    		BufferedReader is = new BufferedReader(new 
    		                       InputStreamReader(sock.getInputStream()));

            System.out.println(is.readLine());

          }catch(SocketException e){ System.out.println("SocketException " + e); 
          }catch(IOException e){ System.out.println("IOException " + e);

             // Finalmente cerrar el socket desde el lado cliente

	      } finally{
			  try{
				  sock.close(); 
			  } catch(IOException ie){ 
					System.out.println("Error de cerrado :" + ie.getMessage()); 
			  } 
		  }  // finally

    } // main

 }   // Clase Cliente