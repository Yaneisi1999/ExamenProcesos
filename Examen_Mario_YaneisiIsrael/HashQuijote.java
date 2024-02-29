package Hash;

	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectOutputStream;
	import java.security.MessageDigest;
	import java.security.NoSuchAlgorithmException;

	public class HashQuijote {
	    public static void main(String args[]) {
	        try {
	            FileOutputStream fileout = new FileOutputStream("QUIJOTE_HASH.DAT");
	            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            String datos = "Don Quijote de la Mancha.\n"
	                         + "En un lugar de la Mancha de cuyo nombre no quiero acordarme,\n"
	                         + "no ha mucho tiempo que vivía un hidalgo de los de lanza en\n"
	                         + "astillero, adarga antigua, rocín flaco y galgo corredor. Una olla\n"
	                         + "de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos\n"
	                         + "los sábados, lantejas los viernes, algún palomino de añadidura\n"
	                         + "los sabados, consumían las tres partes de su hacienda. El resto de ella\n"
	                         + "concluían sayo de velarte, calzas de velludo para las fiestas con\n"
	                         + "sus pantunflos de lo mismo, y los días de entresemana se honraba con su\n"
	                         + "vellorí de lo más fino";
	            byte dataBytes[] = datos.getBytes();
	            md.update(dataBytes); 
	            byte resumen[] = md.digest(); 
	            dataOS.writeObject(datos); 
	            dataOS.writeObject(resumen); 
	            dataOS.close();
	            fileout.close();
	            System.out.println("Datos:" + datos);
	            System.out.println("\nResumen SHA-256 del Quijote creado con éxito");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	    }
	}


