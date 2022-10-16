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
				int opcaoMenu = sc.nextInt();
				switch (opcaoMenu) {
				case 1:
					service.criarPessoaAluno();
					break;
				case 2:
					Menu.menuMostrar();
					int opcaoMostrar = sc.nextInt();
					if (opcaoMostrar < 0 || opcaoMostrar > 3) {
						throw new SistemaException("Opção inválida!!");
					}					
					service.mostrarPessoasAlunos(opcaoMostrar);
					break;
				case 3:
					service.mostrarPessoasAlunos(1);
					Menu.menuPorID();
					int opcaoId = sc.nextInt();		
					if (opcaoId == 0) {
						break;
					}
					service.atualizarDados(opcaoId);
					break;
				case 4:
					System.out.println("Informe o número correspondente à pessoa que deseja deletar:");
					service.mostrarPessoasAlunos(1);
					opcaoId = sc.nextInt();
					service.deletarPessoa(opcaoId);
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