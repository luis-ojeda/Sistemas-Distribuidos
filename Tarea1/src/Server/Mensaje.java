//package Server;


import java.util.ArrayList;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.IllegalBlockingModeException;

public class Mensaje {
	ArrayList<String> mensajes = new ArrayList<String>();

	ChatFrameServer  gui;
	String todo;
	int Max_buffer = 1000;
	//
	FileWriter fichero = null;
	PrintWriter pw = null;
	// variables necesarias para guardar el historico
	BufferedWriter bufferedWriter ;
	BufferedReader bufferedReader ;
	String temp;
	String line ;
	String lineW ;

	public final static String Archivo_de_registro = "./../../data/Archivo.txt"; 


	public Mensaje(ChatFrameServer  gui2){
		this.gui = gui2;

	}

	public void set(String men){
		temp="";
		lineW = "";
		try {

			// se crea un temporal con todo el registro anterior 
			bufferedReader = new BufferedReader(new FileReader(Archivo_de_registro));
			while((lineW = bufferedReader.readLine())!=null) {
        if (temp == ""){
          temp = lineW;
        } else {
          temp= temp +"\n"+ lineW;
        }
      }
			bufferedReader.close();
			//Escritura
			bufferedWriter = new BufferedWriter(new FileWriter(Archivo_de_registro));
			bufferedWriter.append(temp);
      bufferedWriter.append("\n");
			bufferedWriter.append(men);
			bufferedWriter.flush();
			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public void Mensaje_al_cliente(String clave, Socket sock, ServerSocket sersock){

		if((clave =="GET" )     ||(true)    ){
			try {
				envio_paquetes(  sock, sersock);
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
	public  void envio_paquetes (Socket sock, ServerSocket sersock) throws IOException {
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		ServerSocket servsock = null;
		int particiones;
		try {
			
			File myFile = new File (Archivo_de_registro);
			byte [] mybytearray  = new byte [(int)myFile.length()];
			byte [] buffertemp = new byte [Max_buffer];
			fis = new FileInputStream(myFile);
			bis = new BufferedInputStream(fis);
			particiones = (int)Math.ceil((float)mybytearray.length / (float)Max_buffer ) ;
			// mandamos metadata
			System.out.println("Enviando metadata...");
			System.out.println("Particiones = "+particiones);
			System.out.println("Largo archivo = "+mybytearray.length);
			PrintStream ios = new PrintStream(sock.getOutputStream());
			//primero servidor manda cantidad de paquetes
			ios.println(particiones);
    		//luego servidor manda tamanio paquete
			ios.println(Max_buffer);

			sock.close();
			sock = sersock.accept();
			
			
			// luego mandamos paquetes
			for (int i= 0;    i < particiones   ;i++){
				if((i+1)*Max_buffer <mybytearray.length){
					bis.read(mybytearray,i*Max_buffer ,Max_buffer);

					os = sock.getOutputStream();
					System.out.println("Sending " + Archivo_de_registro + "(" + mybytearray.length + " bytes)");
					os.write(mybytearray,i*Max_buffer ,Max_buffer);
					os.flush();
				}else{
					bis.read(mybytearray,i*Max_buffer ,mybytearray.length-i*Max_buffer);

					os = sock.getOutputStream();
					System.out.println("Sending " + Archivo_de_registro + "(" + mybytearray.length + " bytes)");
					os.write(mybytearray,i*Max_buffer ,mybytearray.length-i*Max_buffer);
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
