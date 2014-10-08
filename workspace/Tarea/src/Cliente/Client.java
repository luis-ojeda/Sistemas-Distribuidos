package Cliente;
import java.net.*;
import java.io.*;

import Server.ChatFrameServer;
import Server.ServerMulticaster;
import Server.ServerTCP;

public class Client {
	Client(){
	}
	
	 public static void main(String args[]) {

	    	//Date cumpleanos = new Date(1963, 8, 30);
		 
		 TcpCliente tcpcliente = new TcpCliente("Tcpcliente");
		 ChatClient chatclient = new ChatClient("chatclient");
		 
		 
		 chatclient.nombreusuario("cliente1");
		 chatclient.start();
		 tcpcliente.start();

	    } // main
   
} 
