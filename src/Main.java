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
				Menu.MENU_PRINCIPAL();
				int opcaoMenu = sc.nextInt();
				switch (opcaoMenu) {
				case 1:
					service.criarPessoaAluno();
					break;
				case 2:
					Menu.MENU_MOSTRAR();
					int opcaoMostrar = sc.nextInt();					
					if (opcaoMostrar == 0) {
						break;
					} else if (opcaoMostrar < 0 || opcaoMostrar > 3) {
						throw new SistemaException("Opção inválida!!");
					}
					service.mostrarPessoasAlunos(opcaoMostrar);
					sc.nextLine();
					int cadastroId =  sc.nextInt();
					if (cadastroId == 0) {
						break;
					}
					if (opcaoMostrar >= 1 || opcaoMostrar <= 3) {
						service.escolherAlteracao(cadastroId);
					}
					break;
				case 3:
					Menu.MENU_BUSCAR();
					int opcaoBuscar = sc.nextInt();
					cadastroId = 0;
					switch (opcaoBuscar) {
					case 1:
						cadastroId = service.pesquisarPorNome();
						break;
					case 2:
						System.out.println("difite o ID");
						cadastroId = sc.nextInt();
						break;
					case 0:						
						break;
					}
					if (opcaoBuscar == 1 || opcaoBuscar == 2) {
						service.escolherAlteracao(cadastroId);
					}
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