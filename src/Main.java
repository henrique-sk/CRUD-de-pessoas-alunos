import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import service.Service;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(new Locale("pt", "Brazil"));	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		Service service = new Service(sc);
		
		service.mostrarTodasPessoas();
		
		
	}
	

}
