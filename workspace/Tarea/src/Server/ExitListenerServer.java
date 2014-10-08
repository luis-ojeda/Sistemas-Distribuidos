package Server;
import java.awt.event.*;

public class ExitListenerServer extends WindowAdapter {

	ServerMulticaster client;

   public ExitListenerServer(ServerMulticaster client) {
      this.client = client;
   }
      
   public void windowClosing(WindowEvent e) {
      client.disconnect();
      System.exit(0);
   }
}
