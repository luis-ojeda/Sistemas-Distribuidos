package Server;

public class ServerGUI {
	//parametros GUI
    ChatFrameServer  gui;
	static String name;	
	
	public void GUI() {     
		  this.name = "server";
	      // crear GUI y manejar eventos:
	      // - despues de ingreso de texto llamar sendTextToChat();
	      // - despues de cerrar ventana llama a disconnect(). 
	      gui = new ChatFrameServer("Chat con IP-Multicast: "+name);
	      gui.input.addKeyListener (new EnterListenerServer(this,gui));
	      gui.addWindowListener(new ExitListenerServer(this));
	}
}
