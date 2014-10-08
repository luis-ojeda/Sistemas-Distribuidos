package Server;

import java.awt.event.*;

public class EnterListenerServer extends KeyAdapter {
   
	ServerMulticaster servermulticaster;
   ChatFrameServer gui;
   String message;
   String[ ] mensajes = new String[100];
  

   public EnterListenerServer (ChatFrameServer gui2, ServerMulticaster servermulticaster2,  String[ ] mensajes2) {
      this.gui = gui2;
       this.servermulticaster = servermulticaster2;
       this.mensajes= mensajes2 ;
   }   

   public int ultimo_elemento(String[ ] lista){
	   for(int i=0; i<lista.length; i++){
			if(lista[i]==null){
				return i;
			}
		}
	return -1;
   }
   public void agrega_a_buffer(String men){
	   int ultimo= ultimo_elemento(mensajes);
		if   (ultimo >=0){
			mensajes[ultimo] = men;
		}
   }
   
   
   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
    	  servermulticaster.sendTextToChat(gui.input.getText());
    	  message = "server: "+gui.input.getText()+"\n";
    	  agrega_a_buffer(message);
    	  gui.output.append(message);
          gui.input.setText("");
      }
   }
}
