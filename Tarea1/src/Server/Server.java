import java.util.Scanner;

public class Server  {
	
  static ChatFrameServer  gui;
  static Mensaje mensajes ;
  static int portS = 9025;
   
	
  public static void main(String args[]) {
    gui = new ChatFrameServer("Chat con IP-Multicast: Server");
    mensajes = new Mensaje(gui);
    int port = 6789;
    Scanner consola = new Scanner( System.in );
    System.out.println("Ingrese IP Multicast");
      
    String MIP = consola.next();
    if (MIP.equals("@")) {
      MIP ="231.0.0.1";
      portS = 9025;
    } else {
      System.out.println("Ingrese puerto Multicast");
      port = Integer.parseInt(consola.next());
      System.out.println("Ingrese puerto Servidor");
      portS = Integer.parseInt(consola.next());
    }
    
    ServerMulticaster servemulticaster = new ServerMulticaster("servemulticaster", MIP, port);
    
    ServerTCP servertcp = new ServerTCP("servertcp",mensajes, portS);
    ServerGUI servergui = new ServerGUI(gui,servemulticaster,mensajes);
    servergui.GUI();
      
    servertcp.start();
    servemulticaster.start();
  } // main
}  // Clase Server