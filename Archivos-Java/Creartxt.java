package Clases;
import java.io.*;
import javax.swing.JOptionPane;
public class Creartxt{
public void Creatxt(String titulo,String nombre){	
	File f;
	FileWriter w;
	BufferedWriter bw;
	PrintWriter wr;
try{
f = new File(nombre);
w = new FileWriter(f);
bw = new BufferedWriter (w);
wr = new PrintWriter(bw);

wr.write(titulo);
wr.close();
bw.close();
}catch(Exception e){System.err.println("Error");}
}
}
