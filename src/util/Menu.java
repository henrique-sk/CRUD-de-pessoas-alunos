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
				+ "Bem-vindo ao sistema de gerenciamento de cadastros:\n"
				+ CABECALHO
				+ "1 - Criar cadastro\n"
				+ "2 - Listagem de cadastros\n"
				+ "3 - Buscar por nome\n"
				+ "4 - Buscar por ID\n"
				+ "0 - Encerrar programa");
	}
	
	public static final void MENU_MOSTRAR() {
		System.out.println(CABECALHO
				+ "1 - Mostrar todos cadastros\n"
				+ "2 - Mostrar somente alunos\n"
				+ "3 - Mostrar somente não alunos\n"
				+ RETORNAR_MENU);
	}
	
	
//	public static final void MENU_BUSCAR() {
//		System.out.println(CABECALHO
//				+ "1 - Buscar por nome\n"
//				+ "2 - Buscar por ID\n"
//				+ RETORNAR_MENU);
//	}
	
	public static final void MENU_ALTERAR() {
		System.out.println(CABECALHO
				+ "1 - Atualizar cadastro\n"
				+ "2 - Ramover cadastro\n"
				+ RETORNAR_MENU);
	}
	
	public static final void MENU_ATUALIZAR(Pessoa pessoa) {
		System.out.println("Dados atuais do cadastro selecionado:\n"
				+ pessoa + "\n"
				+ CABECALHO
				+ "1 - Alterar nome\n"
				+ "2 - Alterar telefone\n"
				+ "3 - Alterar data de nascimento\n"
				+ "4 - Inserir/alterar nota final do curso\n"
				+ "5 - Remover cadastro\n"
				+ RETORNAR_MENU);
	}

}
