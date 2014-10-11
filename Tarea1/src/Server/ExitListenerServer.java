//package Server;
import java.awt.event.*;

public class ExitListenerServer extends WindowAdapter {

	ServerMulticaster servermulticaster;

   public ExitListenerServer(ServerMulticaster servermulticaster2) {
	   this.servermulticaster = servermulticaster2;
   }
      
   public void windowClosing(WindowEvent e) {
	   servermulticaster.disconnect();
      System.exit(0);
   }
}
