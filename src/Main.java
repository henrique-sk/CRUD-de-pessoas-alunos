import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Aluno;
import model.Pessoa;
import service.Service;
import util.Data;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(new Locale("pt", "Brazil"));	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Scanner sc = new Scanner(System.in);
		
		Service service = new Service(sc);
		
		Pessoa pessoa = new Pessoa("João", 7474, "12/08/2020", "12/08/2020", "12/08/2020");
		Pessoa aluno = new Aluno("João", 7474, "12/08/2020", "12/08/2020", "12/08/2020", 872365);
		
//		System.out.println(pessoa);
//		System.out.println(aluno);
//		
//		List<Pessoa> todos = Arrays.asList(pessoa, aluno);
//		
//		System.out.println(todos);
//		System.out.println(aluno.getClass());
		
		
		service.mostrarTodasPessoas();		
		service.mostrarTodosAlunos();
		
		Date teste = Data.stringParaData("25/12/w2020");

		System.out.println(teste);
		
	}
	

}
