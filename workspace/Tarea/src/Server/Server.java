package Server;






public class Server  {
	
	static ChatFrameServer  gui;
	static String[ ] mensajes = new String[100];
	
    public static void main(String args[]) {
    	
    	
    	
    	ServerMulticaster servemulticaster = new ServerMulticaster("servemulticaster");
    	ServerGUI servergui = new ServerGUI(gui,servemulticaster,mensajes);
    	ServerTCP servertcp = new ServerTCP("servertcp",mensajes);
     	servergui.GUI();
     	servertcp.start();
    	servemulticaster.start();
    	

    } // main
    
    
    
    
}  // Clase Server