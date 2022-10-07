package util;

public class Menu {
	
	private static final String cabecalho = ""
			+ "--------------------------------------------\n"
			+ "Digite a opção desejada:\n";
	
	public static final void menu1() {
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

}
