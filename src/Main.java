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
				sc.nextLine();
				switch (opcaoMenu) {
				case 1:
					service.criarPessoaAluno();
					break;
				case 2:
					service.mostrarPessoasAlunos();
					break;
				case 3:
					service.pesquisarPorNome();
					break;
				case 4:
					service.atualizarDados(service.receberId());
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