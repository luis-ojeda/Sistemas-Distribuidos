package Cliente;
import java.net.*;
import java.io.*;

import Server.ChatFrameServer;
import Server.ServerMulticaster;
import Server.ServerTCP;

public class Client {
	
	
	 public static void main(String args[]) {
<<<<<<< HEAD
	    	//Date cumpleaños = new Date(1963, 8, 30);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of c951bd6... Revert "Se modularizo el servidor"
=======
	    	//Date cumpleanos = new Date(1963, 8, 30);
		 ServerGUI servergui = new ServerGUI("ServerGUI");
		 
>>>>>>> origin/master
<<<<<<< HEAD
=======
		 ServerGUI servergui = new ServerGUI("ServerGUI");
		 
>>>>>>> parent of fdefd2b... Se modularizo el servidor
=======
		 ServerGUI servergui = new ServerGUI("ServerGUI");
		 
>>>>>>> parent of fdefd2b... Se modularizo el servidor
=======
>>>>>>> parent of c951bd6... Revert "Se modularizo el servidor"
		 
		 TcpCliente tcpcliente = new TcpCliente("Tcpcliente");
		 ChatClient chatclient = new ChatClient("chatclient");
		 
		 
		 chatclient.nombreusuario("cliente1");
		 chatclient.start();
		 tcpcliente.start();

	    } // main
   
} 
