import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient extends Thread{
	
  ChatFrame gui;
  String name;
  
  InetAddress group;
  MulticastSocket socket;
  int port = 6789;
  String ipS;
  int portS;
   
  public ChatClient(String str) {
    super(str);
  }

  public void nombreusuario(String nombre){
    name= nombre;
  }
  
  public void run() {
    Scanner consola = new Scanner( System.in );
    System.out.println("Ingrese IP Multicast");
      
    String MIP = consola.next();
    if (MIP.equals("@")) {
      MIP ="231.0.0.1";
    } else {
      System.out.println("Ingrese puerto Multicast");
      port = Integer.parseInt(consola.next());
    }
    System.out.println("Ingrese IP Servidor");
    String ipS = consola.next();
    if (!ipS.equals("@")) {
      System.out.println("Ingrese puerto Servidor");
      portS = Integer.parseInt(consola.next());
    } else {
      ipS = "localhost";
      portS = 9025;
    }
      
    this.name = name;
    gui = new ChatFrame(name);
    gui.ip.addKeyListener (new EnterListener(this,gui));
    gui.addWindowListener(new ExitListener(this));
	     
    try {
      socket = new MulticastSocket(port);	// crear socket de multicast
	      
      // Registrarse en un grupo de Multicast 
      group = InetAddress.getByName(MIP);
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
  
  public void getChat() {
    String name = gui.getTitle();
    gui.output.append(name + ": Solicitando registro al servidor "+ipS+"\n");
    TcpCliente tcpcliente = new TcpCliente("Tcpcliente", ipS, portS);
    tcpcliente.start();
    gui.output.append(name + ": Solicitud terminada");
  }

  public void disconnect() {
     
  }

   
} 
