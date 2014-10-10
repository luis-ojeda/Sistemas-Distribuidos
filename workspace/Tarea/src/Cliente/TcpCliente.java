package Cliente;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.io.BufferedInputStream;
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
	//String PATH_FILE_TO_RECEIVED = "~/Downloads/"; // a ser cambiado
	String PATH_FILE_TO_RECEIVED = "prueba.txt"; // a ser cambiado
	//constructor de la clase
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

			sock= new Socket(ip,port);
			ps= new	PrintStream(sock.getOutputStream());
			ps.println("Hola desde el cliente!");
			
			BufferedInputStream fis = new BufferedInputStream(sock.getInputStream());
			BufferedReader is = new BufferedReader(new 
					InputStreamReader(fis));
			
			//primero servidor manda cantidad de paquetes
			linea = is.readLine();
			System.out.println("Cantidad de paquetes a recibir:" + linea);
			int numPackets = Integer.parseInt(linea);
			
			//luego servidor manda tamanio paquete
			linea = is.readLine();
			System.out.println("Packet Size:" + linea);
			FILE_SIZE = Integer.parseInt(linea);
			
			fos = new FileOutputStream(PATH_FILE_TO_RECEIVED/*+ip.toString()+".txt"*/);
			byte [] tempbytearray  = new byte [FILE_SIZE];
			int next = 0;
			
			byte [] archivo = new byte [FILE_SIZE*numPackets];
			
			sock.close();
			
			// abrimos nuevamente la conexion con el servidor para mandar flujo de bytes
			sock = new Socket(ip,port);
			System.out.println("Reconectando:" + sock);
			InputStream copia = sock.getInputStream();
			int current = 0;
			
			//finalmente manda paquetes
			for (int i = 0; i<numPackets; i++){
				
				
				try {
					int bytesRead = copia.read(tempbytearray,0,tempbytearray.length);
					current = bytesRead;
					do {
						bytesRead = copia.read(tempbytearray, current, (tempbytearray.length-current));
						if(bytesRead >= 0)
							current += bytesRead;
					} while(((current < FILE_SIZE)&&(bytesRead > -1))||(current <= -1));
					//copiamos los bytes recibidos al arreglo de bytes del archivo
					System.arraycopy(tempbytearray, 0, archivo, next, current);

//					String str = new String(tempbytearray, "UTF-8");
//					System.out.println("archivo:" + str +"/");
					
					next = next+current;
					System.out.println("Received packet " + (i+1) + ", total bytes downloaded=" + next);
				}
				// manejo de paquetes perdidos
				finally {
					//if (sock != null) sock.close();
				}
			}
			
			fos.write(archivo,0,next);
			if (fos != null) fos.close();
			if (sock != null) sock.close();
			System.out.println("Historial recibido.");

		}catch(SocketException e){ System.out.println("SocketException " + e); 
		}catch(IOException e){ e.printStackTrace();

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