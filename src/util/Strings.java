package util;

public enum Strings {
	/*
	 * RECORRENTES
	 */
	SEPARADOR("--------------------------------------------------------\n"),
	SEPARADOR_DUPLO("========================================================\n"),
	OPCAO_INVALIDA("Opção inválida!!"),
	RETORNANDO_MENU("Retornando ao Menu Principal!"),
	OPCAO_INVALIDA_RETORNANDO(OPCAO_INVALIDA
			+ "\n" + RETORNANDO_MENU),
	
	/* 
	 * MENUS 
	 */
	OPCAO_DESEJADA(">>> Digite a opção desejada: \n"),
	CABECALHO(
			SEPARADOR
			 + "" +OPCAO_DESEJADA),
	MENU_PRINCIPAL(""
			+ SEPARADOR_DUPLO
			+ "Bem-vindo ao sistema de gerenciamento de cadastros:\n"
			+ CABECALHO
			+ "1 - Criar cadastro\n"
			+ "2 - Listagem de cadastros\n"
			+ "3 - Buscar por nome\n"
			+ "4 - Buscar por ID\n"
			+ "0 - Encerrar programa"),
	RETORNAR_MENU("0 - Retornar ao Menu Principal"),
	MENU_CADASTROS(
			CABECALHO
			+ "1 - Mostrar todos cadastros\n"
			+ "2 - Mostrar somente alunos\n"
			+ "3 - Mostrar somente não alunos\n"
			+ RETORNAR_MENU),
	OPCOES_ATUALIZAR("\n"
			+ CABECALHO
			+ "1 - Alterar nome\n"
			+ "2 - Alterar telefone\n"
			+ "3 - Alterar data de nascimento\n"
			+ "4 - Inserir/alterar nota final do curso\n"
			+ "5 - Remover cadastro\n"
			+ RETORNAR_MENU),
	ENCERRAMENTO("Obrigado por utilizar nosso sistema. Até logo!"),
	
	/*
	 * CADASTRAMENTO
	 */
	CADASTRAMENTO(SEPARADOR
			+ ">>> Cadastramento:"),
	NOME("Digite o nome: "),
	TELEFONE("Digite o telefone com DDD. Somente os 11 números (###########): "),
	NASCIMENTO("Digite a data de nascimento conforme indicado (DD/MM/AAAA): "),
	TEM_NOTA("Deseja inserir uma nota final do curso? (S/N)"),
	NOTA("Digite a nota final do curso: (entre 0 e 10)"),
	CADASTRADO("Cadastro realizado com sucesso!"
			+ "\nID n° "),
	
	/*
	 * SELEÇÃO/ALTERAÇÃO DE CADASTROS
	 */
	LISTAGEM(SEPARADOR
			+ ">>> Listagem:"),
	INFORME_ID(SEPARADOR
			+ ">>> Informe o ID para selecionar o cadastro correspondente "
			+ "(ou '0' para retornar ao menu principal):"),
	INFORME_NOME("Digite o nome ou parte do nome da pessoa ou aluno(a): "),
	CABECALHO_DADOS("Dados atuais do cadastro selecionado:\n"),
	CABECALHO_ATUALIZAR(SEPARADOR + ""
			+ CABECALHO_DADOS),
	SEM_OCORRENCIA("Nenhuma ocorrência com '"),
	SEM_OCORRENCIA_2("' encontrada!\n"
			+ RETORNANDO_MENU),
	VIROU_ALUNO(" agora é um(a) aluno(a)!"),
	ATUALIZADO("Atualizado com sucesso!"),
	NAO_ENCONTRADO("Cadastro não encontrado!"),
	NOVO_ALUNO("Novo(a) aluno(a) cadastrado(a) com sucesso!"),
	REMOVER_CADASTRO(">>> Tem certeza que deseja remover permanentemente o cadastro de "),
	REMOVER_CADASTRO_2("? (S/N)"),
	REMOVIDO("Cadastro removido com sucesso!");
	
	private final String texto;	

	Strings(final String texto) {
		this.texto = texto;
	}

	@Override
	public String toString() {
		return texto;
	}

}
