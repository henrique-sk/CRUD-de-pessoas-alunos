import java.util.InputMismatchException;
import java.util.Scanner;

import exception.SistemaException;
import service.Service;
import util.Menu;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Service service = new Service(sc);

		boolean continua = true;
		do {
			try {
				Menu.PRINCIPAL();
				int opcaoMenu = sc.nextInt();
				switch (opcaoMenu) {
				case 1:
					service.criarPessoaAluno();
					break;
				case 2:
					Menu.MOSTRAR();
					service.mostrarPessoasAlunos();
					int cadastroId =  sc.nextInt();
					if (cadastroId == 0) {
						break;
					}
					service.atualizarDados(cadastroId);;
					break;
				case 3:
					cadastroId = service.pesquisarPorNome();
					if (cadastroId != 0) {
						service.atualizarDados(cadastroId);
					}
					break;
				case 4:
					System.out.println("Digite o ID:");
					cadastroId = sc.nextInt();
					service.atualizarDados(cadastroId);
					break;
				case 0:
					System.out.println("Obrigado por utilizar nosso sistema. Até logo!");
					continua = false;
					break;
				default:
					System.out.println("Alternativa inválida. Tente novamente!");
					break;
				}
			} catch (SistemaException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida!!");
				sc.nextLine();
			} finally {
				Thread.sleep(1500l);
			}
		} while (continua);
	}

}