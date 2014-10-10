package Cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


class TcpCliente extends Thread{
	String ipS;
	String linea;
	int port;
	int FILE_SIZE = 6022386;
	FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    //String PATH_FILE_TO_RECEIVED = "~/Downloads/"; // a ser cambiado
    String PATH_FILE_TO_RECEIVED = "prueba.txt"; // a ser cambiado
	//contructor de la clase
	public TcpCliente(String str, String ip, String port) {
        super(str);
        this.ipS = ip;
        this.port = Integer.parseInt(port);
    }
	
	
	
    public void  run() {
    	
         Socket sock=null; DataInputStream dis=null; PrintStream ps=null;

         try { 
			System.out.println(" Tratando de conectarse!");
			InetAddress ip =InetAddress.getByName(ipS);

			// Crear un socket al que se asocie el servidor.

            sock= new Socket(ip,port); ps= new 
            PrintStream(sock.getOutputStream());
			ps.println("Hola desde el cliente!");
    		BufferedReader is = new BufferedReader(new 
    		                       InputStreamReader(sock.getInputStream()));
    		//primero servidor manda cantidad de paquetes
    		linea = is.readLine();
    		System.out.println("Cantidad de paquetes a recibir:" + linea);
    		int numPackets = Integer.parseInt(linea);
    		//luego servidor manda tamanio paquete
    		linea = is.readLine();
    		System.out.println("Packet Size:" + linea);
    		FILE_SIZE = Integer.parseInt(linea);
    		fos = new FileOutputStream(PATH_FILE_TO_RECEIVED/*+ip.toString()+".txt"*/);
		    bos = new BufferedOutputStream(fos);
		    byte [] mybytearray  = new byte [FILE_SIZE];
		    int next = 0;
    		//finalmente manda paquetes
    		for (int i = 0; i<numPackets; i++){
    			try {
    				InputStream fis = sock.getInputStream();
    				int bytesRead = fis.read(mybytearray,0,mybytearray.length);
    				int current = bytesRead;
    				do {
    					bytesRead = fis.read(mybytearray, current, (mybytearray.length-current));
    					if(bytesRead >= 0) current += bytesRead;
    				} while(bytesRead > -1);

    				bos.write(mybytearray, next, current);
    				next = next+current;
    				System.out.println("Received packet " + i + ", total bytes downloaded=" + next);
    		    }
    			// manejo de paquetes perdidos
    		    finally {
    		    	//if (sock != null) sock.close();
    		    }
    		}
    		if (fos != null) fos.close();
		    if (bos != null) bos.close();
		    if (sock != null) sock.close();
    		bos.flush();
    		System.out.println("Historial recibido.");
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