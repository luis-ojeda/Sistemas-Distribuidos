package Server;
import java.awt.event.*;

public class ExitListenerServer extends WindowAdapter {

	ServerGUI server;
	ServerMulticaster servermulticaster;

   public ExitListenerServer(ServerGUI serverGUI) {
      this.server = serverGUI;
   }
      
   public void windowClosing(WindowEvent e) {
	   servermulticaster.disconnect();
      System.exit(0);
   }
}
