import java.util.InputMismatchException;
import java.util.Scanner;

import exception.SistemaException;
import service.Service;
import util.Strings;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Service service = new Service(sc);

		boolean continua = true;
		do {
			try {
				System.out.println(Strings.MENU_PRINCIPAL.toString());
				int opcaoMenu = sc.nextInt();
				sc.nextLine();
				switch (opcaoMenu) {
				case 1:
					service.criarCadastro();
					break;
				case 2:
					service.listarCadastros();
					break;
				case 3:
					service.pesquisarPorNome();
					break;
				case 4:
					service.atualizarDados(service.receberId());
					break;
				case 0:
					System.out.println(Strings.ENCERRAMENTO.toString());
					continua = false;
					break;
				default:
					System.out.println(Strings.OPCAO_INVALIDA.toString());
					break;
				}
			} catch (SistemaException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(Strings.OPCAO_INVALIDA.toString());
				sc.next();
			} finally {
				Thread.sleep(1500l);
			}
		} while (continua);
	}

}