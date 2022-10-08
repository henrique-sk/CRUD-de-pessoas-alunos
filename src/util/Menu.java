package util;

public class Menu {
	
	private static final String cabecalho = ""
			+ "--------------------------------------------\n"
			+ "Digite a opção desejada:\n";
	
	public static final void menuPrincipal() {
		System.out.println(""
				+ "============================================\n"
				+ "Bem-vindo ao sistema de gerenciamento de pessoas/alunos\n"
				+ cabecalho
				+ "1 - Criar pessoa/aluno\n"
				+ "2 - Mostrar todas pessoas/alunos\n"
				+ "3 - Atualizar dados de pessoas/alunos\n"
				+ "4 - Deletar uma pessoa/aluno"
				+ "0 - Encerrar programa");
	}
	
	public static final void menuMostrar() {
		System.out.println(""
				+ cabecalho
				+ "1 - Mostrar todas pessoas\n"
				+ "2 - Mostrar somente alunos");
	}
	
	public static final void porID() {
		System.out.println(""
				+ "--------------------------------------------\n"
				+ "Digite o número correspondente a pessoa/aluno desejada:\n");
	}

}
