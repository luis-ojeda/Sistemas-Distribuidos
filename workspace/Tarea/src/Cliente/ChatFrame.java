package Cliente;
import java.awt.*;

public class ChatFrame extends Frame { 
  
   protected TextArea output; 
   protected TextField ip;
   protected TextField port;
  
   public ChatFrame (String title){
      super (title); 
    
      setLayout (new BorderLayout ()); 
      add ("Center", output = new TextArea ()); 
      output.setEditable (false); 
      add ("South", ip = new TextField ()); 
      add ("East", port = new TextField ());
      pack (); 
      show (); 
      ip.requestFocus ();
   }
  
/*   public static void main (String args[]){ 
      new ChatFrame("Chat "); 
   }
*/

}

