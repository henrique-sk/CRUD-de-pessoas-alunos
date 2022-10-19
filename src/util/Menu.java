package util;

import model.Pessoa;

public class Menu {
	
	private static final String CABECALHO = ""
			+ "--------------------------------------------------------\n"
			+ "Digite a opção desejada:\n";
	
	public static final String RETORNAR_MENU = "0 - Retornar ao Menu Principal";
	
	public static final void MENU_PRINCIPAL() {
		System.out.println(""
				+ "========================================================\n"
				+ "Bem-vindo ao sistema de gerenciamento de pessoas/alunos:\n"
				+ CABECALHO
				+ "1 - Criar pessoa/aluno\n"
				+ "2 - Mostrar todas pessoas/alunos\n"
				+ "3 - Atualizar dados de pessoas/alunos\n"
				+ "4 - Deletar uma pessoa/aluno\n"
				+ "0 - Encerrar programa");
	}
	
	public static final void MENU_MOSTRAR() {
		System.out.println(CABECALHO
				+ "1 - Mostrar todas pessoas\n"
				+ "2 - Mostrar somente alunos\n"
				+ "3 - Mostrar todas pessoas que não são alunos\n"
				+ RETORNAR_MENU);
	}
	
	
	public static final void MENU_PESQUISAR() {
		System.out.println(CABECALHO
				+ "1 - Pesquisar por nome\n"
				+ "2 - Pesquisar por id\n"
				+ RETORNAR_MENU);
	}
	
	public static final void MENU_ATUALIZAR(Pessoa pessoa) {
		System.out.println("Dados atuais da pessoa/aluno selecionada:\n"
				+ pessoa + "\n"
				+ CABECALHO
				+ "1 - Nome\n"
				+ "2 - Telefone\n"
				+ "3 - Data de nascimento\n"
				+ "4 - Inserir/alterar nota final do curso\n"
				+ RETORNAR_MENU);
	}

}
