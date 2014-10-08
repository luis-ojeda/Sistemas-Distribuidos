package Server;

import java.awt.event.*;

public class EnterListenerServer extends KeyAdapter {
   
	ServerGUI server;
	ServerMulticaster servermulticaster;
   ChatFrameServer gui;
  

   public EnterListenerServer (ServerGUI serverGUI, ChatFrameServer gui2) {
      this.server = serverGUI;
      this.gui = gui2;
   }   

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
    	  servermulticaster.sendTextToChat(gui.input.getText());
          gui.input.setText("");
      }
   }
}
