import java.util.Scanner;

import service.Service;
import util.Menu;

public class Main {

	public static void main(String[] args) {
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
					System.out.println("Listagem de todas pessoas:");
					service.mostrarTodasPessoas();
				} else if (opcao2 == 2) {
					System.out.println("Listagem de todos alunos:");
					service.mostrarTodosAlunos();
				}
				break;
			case 3:
				System.out.println("Listagem de todas pessoas:");
				service.mostrarTodasPessoas();
				System.out.println("Informe o número correspondente à pessoa que deseja atuaizar (ou 0 para retornar ao menu principal): ");
				int opcao3 = sc.nextInt();
				String tipoPessoa;
				if (opcao3 == 0) {
					break;
				} else {
					tipoPessoa = service.tipoPessoa(opcao3);
				}
				service.atualizarDados(opcao3, tipoPessoa);
				
				break;
			case 4:

				break;
			case 0:
				System.out.println("Obrigado por utilizar nosso sistema. Até logo!");
				continua = false;
				break;
			}

		} while (continua);

	}

}
