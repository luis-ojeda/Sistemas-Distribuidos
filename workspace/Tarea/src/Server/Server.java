package Server;






public class Server  {
	
	static ChatFrameServer  gui;
	static String[ ] mensajes = new String[100];
	
    public static void main(String args[]) {
    	
    	
    	
    	ServerMulticaster servemulticaster = new ServerMulticaster("servemulticaster");
    	ServerGUI servergui = new ServerGUI(gui,servemulticaster,mensajes);
     	servergui.GUI();
    	new ServerTCP("servertcp").start();
    	servemulticaster.start();
    	

    } // main
    
    
    
    
}  // Clase Server