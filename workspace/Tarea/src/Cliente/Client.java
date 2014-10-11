package Cliente;
import java.net.*;
import java.io.*;

public class Client {
	Client(){
	}
	
	 public static void main(String args[]) {

	    	//Date cumpleanos = new Date(1963, 8, 30);
		 
		 ChatClient chatclient = new ChatClient("chatclient");
		 
		 
		 chatclient.nombreusuario("cliente1");
		 chatclient.start();

	    } // main
   
} 
