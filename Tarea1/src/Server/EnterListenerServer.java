import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    Calendar cal = Calendar.getInstance();
    cal.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
     
    if (e.getKeyCode()==KeyEvent.VK_ENTER) {
      message = gui.input.getText();
      message = sdf.format(cal.getTime())+" - Server:"+message+"\n";
      servermulticaster.sendTextToChat(message);
      mensajes.set(message);
      //mensajes.muestratodo();
      gui.output.append(message);
      gui.input.setText("");
    }
  }
}
