package Clases;

import java.io.*;

public class leerarchivotxt{


public String leeArchivo(String direccion) {
	String texto = "";	
	try{

	BufferedReader bf = new BufferedReader(new FileReader(direccion));
	String temp = "";
	String bfRead;

	while((bfRead = bf.readLine()) != null){

	temp = temp + bfRead;
	}
texto = temp;
	}catch (Exception e){
System.err.println("No encontre el archivo de texto con ese nombre.");			
						}		
return texto;
}
}

