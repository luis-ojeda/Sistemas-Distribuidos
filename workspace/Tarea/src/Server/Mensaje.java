package Server;

import java.util.ArrayList;

public class Mensaje {
	ArrayList<String> mensajes = new ArrayList<String>();
	 
	 ChatFrameServer  gui;
	 String todo;
	 
	 public Mensaje(ChatFrameServer  gui2){
		 this.gui = gui2;
		 
	 }
	 
	   public void set(String men){
		   mensajes.add(men);
	   }
	   
	  public String  muestratodo(){
		  todo = "todo lo hecho es:" ;
		  for(int i=0; i<mensajes.size(); i++){
				if(mensajes.get(i) ==null){
					break;
				}else{
					todo = todo + mensajes.get(i); 
				//gui.output.append( "todo lo hecho es :\n" + mensajes.get(i) );
					
				}
			}
		  return todo ;
	  }
	   
}
