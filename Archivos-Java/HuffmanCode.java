import java.util.*;
import Clases.leerarchivotxt;
import Clases.Creartxt;
public class HuffmanCode {

	public static void main(String[] args) {




	leerarchivotxt a = new leerarchivotxt();
	String test ="Texto-de-prueba";
	Scanner sc = new Scanner(System.in);
	Scanner scc = new Scanner(System.in);
	System.out.println("Ingresa la direccion del archivo a comprimir seguido del nombre y extension del archivo\n");
	String d = sc.nextLine();
	test = a.leeArchivo(d);
	System.out.println("El texto en el archivo es:\n");
	System.out.println(test); 
        System.out.println("\n");
        int[] charFreqs = new int[256];
        for (char c : test.toCharArray())
            charFreqs[c]++;
        
        HuffmanTree tree = buildTree(charFreqs);
        
        /*System.out.println("CODIGOS");

        System.out.println("SIMBOLO\tFRECUENCIA\tHUFFMAN CODIGO");
        printCodes(tree, new StringBuffer());*/
        
        String encode = encode(tree,test);
	System.out.println("\nSe creará un archivo txt con la infomación comprimida asignale titulo\n");
	String C = scc.nextLine();
	Creartxt aa = new Creartxt();
	aa.Creatxt(encode,C);


        /*System.out.println("\nTexto Comprimido\n");
        System.out.println(encode);*/
        
        /*System.out.println("\nTexto descomprimido\n");
        System.out.println(decode(tree,encode));*/



System.out.println("\nListo la compresión se realizo y se creo un archivo con la información comprimida\n");
System.out.println("Selecciona que quieres ver:\n1.- Tabla de caracteres del texto junto a su frecuecia y condificación\n2.- Texto codificado y decodificado\n3.- Todo\n\n");
	int x=0;
	Scanner leerS = new Scanner(System.in);
	x = leerS.nextInt();
	
switch(x)
{

case 1 : 
{
System.out.println("Aqui esta la tabla de caracteres, frecuencias, y condificación");
System.out.println("SIMBOLO\tFRECUENCIA\tHUFFMAN CODIGO");
printCodes(tree, new StringBuffer());
break;
}
case 2 :
{
System.out.println("\nTexto Comprimido\n");
System.out.println(encode);
System.out.println("\n");
System.out.println("\nTexto descomprimido\n");
System.out.println(decode(tree,encode));
System.out.println("\n");

break;
}
case 3 :
{
System.out.println("Aqui esta la tabla de caracteres, frecuencias, y condificación");
System.out.println("SIMBOLO\tFRECUENCIA\tHUFFMAN CODIGO");
printCodes(tree, new StringBuffer());
System.out.println("\nTexto Comprimido\n");
System.out.println(encode);
System.out.println("\n");
System.out.println("\nTexto descomprimido\n");
System.out.println(decode(tree,encode));
System.out.println("\n");

break;
}
default :
{
System.out.println("No selecionaste correctamente");
break;
}
}
}

    public static HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
 
        for (int i = 0; i < charFreqs.length; i++){
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); 
        }
        
        while (trees.size() > 1) {
            
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
 
            trees.offer(new HuffmanNode(a, b)); 
        }
        return trees.poll();
    }
 
    public static String encode(HuffmanTree tree, String encode){
    	assert tree != null;
    	
    	String encodeText = "";
        for (char c : encode.toCharArray()){
        	encodeText+=(getCodes(tree, new StringBuffer(),c));
        }
    	return encodeText; 
    }
    
    public static String decode(HuffmanTree tree, String encode) {
    	assert tree != null;
    	
    	String decodeText="";
    	HuffmanNode node = (HuffmanNode)tree;
    	for (char code : encode.toCharArray()){
    		if (code == '0'){ 
    		    if (node.left instanceof HuffmanLeaf) { 
    		    	decodeText += ((HuffmanLeaf)node.left).value;  
	                node = (HuffmanNode)tree; 
	    		}else{
	    			node = (HuffmanNode) node.left;  
	    		}
    		}else if (code == '1'){ 
    		    if (node.right instanceof HuffmanLeaf) {
    		    	decodeText += ((HuffmanLeaf)node.right).value; 
	                node = (HuffmanNode)tree; 
	    		}else{
	    			node = (HuffmanNode) node.right; 
	    		}
    		}
    	} 
    	return decodeText; 
    }    
    
    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t\t" + prefix);
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 

            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    

    public static String getCodes(HuffmanTree tree, StringBuffer prefix, char w) {
        assert tree != null;
        
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            if (leaf.value == w ){
            	return prefix.toString();
            }
            
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            prefix.append('0');
            String left = getCodes(node.left, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            String right = getCodes(node.right, prefix,w);
            prefix.deleteCharAt(prefix.length()-1);
            
            if (left==null) return right; else return left;
        }
		return null;
    }

}

