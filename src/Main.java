import java.util.InputMismatchException;
import java.util.Scanner;

import exception.SistemaException;
import service.Service;
import util.Menu;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Service service = new Service();

		boolean continua = true;
		do {
			try {
				Menu.MENU_PRINCIPAL();
				int opcaoMenu = sc.nextInt();
				sc.nextLine();
				switch (opcaoMenu) {
				case 1:
					service.criarPessoaAluno();
					break;
				case 2:
					Menu.MENU_MOSTRAR();
					int opcaoMostrar = sc.nextInt();
					
//					if (opcaoMostrar < 0 || opcaoMostrar > 3) {
//						throw new SistemaException("Opção inválida!!");
//					}					
					service.mostrarPessoasAlunos(opcaoMostrar);
					break;
				case 3:
					Menu.MENU_BUSCAR();
					int opcaoBuscar = sc.nextInt();
					int cadastroId = 0;
					switch (opcaoBuscar) {
					case 1:
						cadastroId = service.pesquisarPorNome();
						break;
					case 2:
						cadastroId = service.pesquisarPorID();
						break;
					case 0:						
						break;
					}
					if (cadastroId != 0 && (opcaoBuscar == 1 || opcaoBuscar == 2)) {
						Menu.MENU_ALTERAR();
						int opcaoAlteracao = sc.nextInt();
						switch (opcaoAlteracao) {						
						case 1:
							service.atualizarDados(cadastroId);
							break;						
						case 2:
							service.deletarPessoa(cadastroId);
							break;
						case 0:
							break;
						}
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
				sc.next();
			} finally {
				Thread.sleep(1500l);
			}

		} while (continua);
		sc.close();
	}

}