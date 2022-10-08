import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import service.Service;
import util.Menu;

public class Main {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(new Locale("pt", "Brazil"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		Service service = new Service(sc);

		boolean continua = true;
		do {
			Menu.menuPrincipal();
			int opcao1 = sc.nextInt();
			sc.nextLine();

			switch (opcao1) {
			case 1:
				service.criarPessoaAluno();
				break;
			case 2:
				Menu.menuMostrar();
				int opcao2 = sc.nextInt();
				if (opcao2 == 1) {
					service.mostrarTodasPessoas();
				} else if (opcao2 == 2) {
					service.mostrarTodosAlunos();
				}
				break;
			case 3:

				break;
			case 4:

				break;
			case 0:

				break;
			}

		} while (continua);

	}

}
