package Server;

public class ServerGUI {
	//parametros GUI
	static String name;	
    ChatFrameServer  gui;
	ServerMulticaster servermulticaster;
	Mensaje mensajes ;
	  
	
	ServerGUI(ChatFrameServer gui2, ServerMulticaster servermulticaster2, Mensaje mensajes2){
		 this.servermulticaster = servermulticaster2;
		 this.gui = gui2;
		 this.mensajes= mensajes2; 
	}
	
	
	public void GUI() {     
		  this.name = "server";
	      // crear GUI y manejar eventos:
	      // - despues de ingreso de texto llamar sendTextToChat();
	      // - despues de cerrar ventana llama a disconnect(). 
	  
	      gui.input.addKeyListener (new EnterListenerServer(gui,servermulticaster,mensajes));
	      gui.addWindowListener(new ExitListenerServer(servermulticaster));
	}
}
