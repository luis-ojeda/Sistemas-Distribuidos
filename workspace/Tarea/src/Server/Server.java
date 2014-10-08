package Server;






public class Server  {
	
	static ChatFrameServer  gui;
	static Mensaje mensajes ;
   
	
    public static void main(String args[]) {
    	
    	
    	
    	gui = new ChatFrameServer("Chat con IP-Multicast: Server");
    	mensajes = new Mensaje(gui);
    	ServerMulticaster servemulticaster = new ServerMulticaster("servemulticaster");
    	ServerTCP servertcp = new ServerTCP("servertcp",mensajes);
    	ServerGUI servergui = new ServerGUI(gui,servemulticaster,mensajes);
    	
     	servergui.GUI();
     	servertcp.start();
    	servemulticaster.start();
    } // main
    
    
    
    
}  // Clase Server