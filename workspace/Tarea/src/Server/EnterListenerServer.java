package Server;

import java.awt.event.*;

public class EnterListenerServer extends KeyAdapter {
   
	ServerMulticaster client;
   ChatFrameServer gui;

   public EnterListenerServer (ServerMulticaster serveMulticaster, ChatFrameServer gui2) {
      this.client = serveMulticaster;
      this.gui = gui2;
   }   

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
          client.sendTextToChat(gui.input.getText());
          gui.input.setText("");
      }
   }
}
