package Cliente;
import java.net.*;
import java.io.*;

import Server.ChatFrameServer;
import Server.ServerGUI;
import Server.ServerMulticaster;
import Server.ServerTCP;

public class Client {
	
	
	 public static void main(String args[]) {
<<<<<<< HEAD
	    	//Date cumpleaños = new Date(1963, 8, 30);
<<<<<<< HEAD
=======
	    	//Date cumpleanos = new Date(1963, 8, 30);
		 ServerGUI servergui = new ServerGUI("ServerGUI");
		 
>>>>>>> origin/master
=======
		 ServerGUI servergui = new ServerGUI("ServerGUI");
		 
>>>>>>> parent of fdefd2b... Se modularizo el servidor
		 
		 TcpCliente tcpcliente = new TcpCliente("Tcpcliente");
		 ChatClient chatclient = new ChatClient("chatclient");
		 
		 
		 chatclient.nombreusuario("cliente1");
		 chatclient.start();
		 tcpcliente.start();

	    } // main
   
} 
