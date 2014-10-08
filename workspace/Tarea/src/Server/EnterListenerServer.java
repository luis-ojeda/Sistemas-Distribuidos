package Server;

import java.awt.event.*;

public class EnterListenerServer extends KeyAdapter {
   
	ServerMulticaster servermulticaster;
   ChatFrameServer gui;
   String message;
   Mensaje mensajes ;
  

   public EnterListenerServer (ChatFrameServer gui2, ServerMulticaster servermulticaster2,  Mensaje mensajes2) {
      this.gui = gui2;
       this.servermulticaster = servermulticaster2;
       this.mensajes= mensajes2 ;
   }   


   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
    	  servermulticaster.sendTextToChat(gui.input.getText());
    	  message = "server: "+gui.input.getText()+"\n";
    	  mensajes.set(message);
    	  //mensajes.muestratodo();
    	  gui.output.append(message);
          gui.input.setText("");
      }
   }
}
