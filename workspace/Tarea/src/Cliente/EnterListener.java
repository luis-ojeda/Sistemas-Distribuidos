package Cliente;
import java.awt.event.*;

public class EnterListener extends KeyAdapter {
   
   ChatClient client;
   ChatFrame gui;

   public EnterListener (ChatClient client, ChatFrame gui) {
      this.client = client;
      this.gui = gui;
   }   

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
//    	  if(gui.ip.getText() == ""){
    		  client.getChat("localhost", "9025");
//    	  } else
//    		  client.getChat(gui.ip.getText(), gui.port.getText());
          //gui.port.setText("");
      }
   }
}
