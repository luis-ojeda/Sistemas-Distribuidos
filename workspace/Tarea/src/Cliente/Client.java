package Cliente;
import java.net.*;
import java.io.*;

import Server.ChatFrameServer;
import Server.ServerGUI;
import Server.ServerMulticaster;
import Server.ServerTCP;

public class Client {
	
	
	 public static void main(String args[]) {
	    	//Date cumpleaños = new Date(1963, 8, 30);
		 ServerGUI servergui = new ServerGUI("ServerGUI");
		 
		 
		 TcpCliente tcpcliente = new TcpCliente("Tcpcliente");
		 ChatClient chatclient = new ChatClient("chatclient");
		 
		 
		 chatclient.nombreusuario("cliente1");
		 chatclient.start();
		 tcpcliente.start();

	    } // main
   
} 
