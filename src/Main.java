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
					} else if (opcao2 == 3) {
						service.mostrarNaoAlunos();
					}
					break;
				case 3:
					service.mostrarTodasPessoas();
					System.out.println(
							"Informe o número correspondente à pessoa que deseja atuaizar (ou '0' para retornar ao menu principal):");
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
					System.out.println("Informe o número correspondente à pessoa que deseja deletar:");
					service.mostrarTodasPessoas();
					int opcao4 = sc.nextInt();
					service.deletarPessoa(opcao4);
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
				sc.next();
			} finally {
				Thread.sleep(1500l);
			}

		} while (continua);

	}

}