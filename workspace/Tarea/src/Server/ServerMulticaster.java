package Server;

import java.net.*;
import java.io.*;


public class ServerMulticaster extends Thread{
  
    // Parametros para el servidor de multicaster
    // El servidor multicaster tiene como finalidad entregar el mensaje a todos los suscritos
    static InetAddress group;
    static MulticastSocket socket;
    static int port = 6789;
    public String mensajes;
    
    //parametros GUI
    ChatFrameServer  gui;
	static String name;	
	
	
	
	//contructor de la clase
	public ServerMulticaster(String str) {
        super(str);
    }
	
	
	////////////////////////////////////////////////
	// implementacion de las funciones de multicast 
	////////////////////////////////////////////////
	
	public void run() {     
		  this.name = "server";
	      // crear GUI y manejar eventos:
	      // - despues de ingreso de texto llamar sendTextToChat();
	      // - despues de cerrar ventana llama a disconnect(). 
	      gui = new ChatFrameServer("Chat con IP-Multicast: "+name);
	      gui.input.addKeyListener (new EnterListenerServer(this,gui));
	      gui.addWindowListener(new ExitListenerServer(this));
	     
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
	
	
	
	public void sendTextToChat(String message) {
	      message = name+": "+message+"\n";
	      byte[] buf = message.getBytes();
	      DatagramPacket dg = new DatagramPacket(buf, buf.length,group,port);
	      try { 
	         socket.send(dg); // envia datagrama al grupo
	      }
	      catch (IOException ex) { 
	         System.out.println(ex);
	      }
	   }

	
	public void disconnect() {}

	
	
}  // Clase Server