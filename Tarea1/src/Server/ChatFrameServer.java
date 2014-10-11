//package Server;
import java.awt.*;

public class ChatFrameServer extends Frame { 
  
   protected TextArea output; 
   protected TextField input;
  
   public ChatFrameServer (String title){
      super (title); 
    
      setLayout (new BorderLayout ()); 
      add ("Center", output = new TextArea ()); 
      output.setEditable (false); 
      add ("South", input = new TextField ()); 
     
      pack (); 
      setVisible (true);
      input.requestFocus (); 
   }
  
/*   public static void main (String args[]){ 
      new ChatFrame("Chat "); 
   }
*/

}

