package Server;


import java.util.ArrayList;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.IllegalBlockingModeException;

public class Mensaje {
	ArrayList<String> mensajes = new ArrayList<String>();

	ChatFrameServer  gui;
	String todo;
	int Max_buffer = 100;
	//
	FileWriter fichero = null;
	PrintWriter pw = null;
	// variables necesarias para guardar el historico
	BufferedWriter bufferedWriter ;
	BufferedReader bufferedReader ;
	String temp;
	String line ;
	String lineW ;

	public final static String Archivo_de_registro = "Archivo.txt"; 


	public Mensaje(ChatFrameServer  gui2){
		this.gui = gui2;

	}

	public void set(String men){
		temp="";
		lineW = "";
		try {

			// se crea un temporal con todo el registro anterior 
			bufferedReader = new BufferedReader(new FileReader(Archivo_de_registro));
			while((lineW = bufferedReader.readLine())!=null)
				temp= temp +"\n"+ lineW;
			bufferedReader.close();
			//Escritura
			bufferedWriter = new BufferedWriter(new FileWriter(Archivo_de_registro));
			bufferedWriter.append(temp);
			bufferedWriter.append(men);
			bufferedWriter.append("\n");
			//bufferedWriter.append("2");
			//bufferedWriter.append("3");
			bufferedWriter.flush();
			bufferedWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void Mensaje_al_cliente(String clave, Socket sock){

		if((clave =="GET" )     ||(true)    ){
			try {
				envio_paquetes(  sock);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String  muestratodo(){
		System.out.println(" mostrar:");
		try {
			//Entrada
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Archivo_de_registro));
			//Buscar si existe una palabra
			while((line = bufferedReader.readLine())!=null){
				System.out.println(line);

			}
		} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		System.out.println("____________________________");
		return "hola";
	}

	// Funcion encargada del envio de los paquetes
	public  void envio_paquetes (Socket sock ) throws IOException {
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		ServerSocket servsock = null;
		int largo_hist_max = 1000;
		int particiones;
		try {
			
			File myFile = new File (Archivo_de_registro);
			byte [] mybytearray  = new byte [(int)myFile.length()];
			byte [] buffertemp = new byte [largo_hist_max];
			fis = new FileInputStream(myFile);
			bis = new BufferedInputStream(fis);
			particiones = (int)Math.ceil((float)mybytearray.length / (float)largo_hist_max ) ;
			//primero mandamos metadata
			System.out.println("Enviando metadata...");
			System.out.println("Particiones = "+particiones);
			System.out.println("Largo archivo = "+mybytearray.length);
			PrintStream ios = new PrintStream(sock.getOutputStream());
			//primero servidor manda cantidad de paquetes
			ios.println(particiones);
    		//luego servidor manda tamanio paquete
			ios.println(largo_hist_max);
			
			
			// luego mandamos paquetes
			for (int i= 0;    i < particiones   ;i++){
				if((i+1)*largo_hist_max <mybytearray.length){
					bis.read(mybytearray,i*largo_hist_max ,largo_hist_max);

					os = sock.getOutputStream();
					System.out.println("Sending " + Archivo_de_registro + "(" + mybytearray.length + " bytes)");
					os.write(buffertemp,0,buffertemp.length);
					os.flush();
				}else{
					bis.read(mybytearray,i*largo_hist_max ,mybytearray.length-i*largo_hist_max);

					os = sock.getOutputStream();
					System.out.println("Sending " + Archivo_de_registro + "(" + mybytearray.length + " bytes)");
					os.write(buffertemp,0,buffertemp.length);
					os.flush();
				}	System.out.println("Enviada parte "+(i+1) + " de " + particiones+"\n");
			}
			
			System.out.println("Enviado todo");


		} catch (IllegalBlockingModeException e){
			e.printStackTrace();
			
		}
		finally {
			
			if (bis != null) bis.close();
			if (os != null) os.close();
		}
	}






}
