package Cliente;

import java.io.*;
import java.net.*;


class TcpCliente extends Thread{
	String ipS;
	int port;
	
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