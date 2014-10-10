package Cliente;
import java.net.*;
import java.io.*;

import Server.ChatFrameServer;
import Server.EnterListenerServer;
import Server.ExitListenerServer;

public class ChatClient extends Thread{
	
   ChatFrame gui;
   String name;
  
   InetAddress group;
   MulticastSocket socket;
   int port = 6789;
   
   
 //contructor de la clase
 	public ChatClient(String str) {
         super(str);
     }
 	
   
   
   public void nombreusuario(String nombre){
	   name= nombre;
   }
  
   public void run() {     
		  this.name = name;
	      // crear GUI y manejar eventos:
	      // - despues de ingreso de texto llamar sendTextToChat();
	      // - despues de cerrar ventana llama a disconnect(). 
	      gui = new ChatFrame(name);
	      gui.port.addKeyListener (new EnterListener(this,gui));
	      gui.ip.addKeyListener (new EnterListener(this,gui));
	      gui.addWindowListener(new ExitListener(this));
	     
	      try {
	         socket = new MulticastSocket(port);	// crear socket de multicast!
	      
	         // Registrarse en un grupo de Multicast 
	         group = InetAddress.getByName("231.0.0.1");
	         socket.joinGroup(group);				// unirse a grupo de multicast
	         gui.output.append("Conectado...\n");

		    // Esperar un mensaje y recibirlo
	        while (true) {
				byte[] buffer = new byte[1000];
				DatagramPacket datagram = new DatagramPacket(buffer,buffer.length);
				socket.receive(datagram);	// se bloquea esperando recibir datagrama
				String message = new String(datagram.getData());
				gui.output.append(message);
	        }
	      } catch (IOException e) { 
	         e.printStackTrace(); 
	      }
	   }
  
   public void getChat(String ipS, String portS) {
	   String name = gui.getTitle();
	   gui.output.append(name + ": Solicitando registro al servidor "+ipS+"\n");
	   TcpCliente tcpcliente = new TcpCliente("Tcpcliente", ipS, portS);
	   tcpcliente.start();
	   gui.output.append(name + ": Solicitud terminada");
	  
//      message = name+": "+message+"\n";
//      byte[] buf = message.getBytes();
//      DatagramPacket dg = new DatagramPacket(buf, buf.length,group,port);
//      try { 
//         socket.send(dg); // envia datagrama al grupo
//      }
//      catch (IOException ex) { 
//         System.out.println(ex);
//      }
   }

   public void disconnect() {}

   
} 
