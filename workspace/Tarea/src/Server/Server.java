package Server;



public class Server  {

    public static void main(String args[]) {
    	
    	new ServerTCP("servertcp").start();
    	new ServerMulticaster("servemulticaster").start();
    	

    } // main
    
    
    
    
}  // Clase Server