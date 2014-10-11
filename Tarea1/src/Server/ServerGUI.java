public class ServerGUI {

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
    gui.input.addKeyListener (new EnterListenerServer(gui,servermulticaster,mensajes));
    gui.addWindowListener(new ExitListenerServer(servermulticaster));
  }
}
