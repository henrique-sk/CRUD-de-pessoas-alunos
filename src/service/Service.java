package service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Data;

public class Service {
	Date hoje = new Date();
	Scanner sc;
	Repository<Pessoa> repository = new Repository<>();
	
	public Service(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Pessoa("Luana", "15777777777", "12/08/2020"));
		this.repository.salvar(new Aluno("Tamires", "42333333333", "12/08/2020", 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", "12/08/2020", 6));
	}
	
	public void criarPessoaAluno() {
		System.out.println("Digite o nome: ");
		String nome = sc.nextLine();		
		System.out.println("Digite o telefone com DDD.\n"
				+ "Somente os 11 números (XXXXXXXXXXX): ");
		String telefone = sc.nextLine().replaceFirst("0", "");		
		System.out.println("Digite a data de nascimento (DD/MM/AAAA): ");
		String dataNascimento = sc.nextLine();		
		System.out.println("Digite a nota final do curso (ou 'N' para concluir o cadastro): ");			
		String notaFinal = sc.nextLine();
		
		Pessoa pessoa;
		if(notaFinal.toUpperCase().equals("N")) {
			pessoa = new Pessoa(nome, telefone, dataNascimento);
		} else {			
			pessoa = new Aluno(nome, telefone, dataNascimento, Double.parseDouble(notaFinal));
		}		
		this.repository.salvar(pessoa);
		
		System.out.println("Cadastrado realizado com sucesso!");
	}
	
	public void mostrarTodasPessoas() {
		System.out.println("Listagem de todas pessoas:");
		List<Pessoa> pessoas = this.repository.buscarTodos();
		
		pessoas.stream().forEach(pessoa -> System.out.println(pessoa));
	}
	
	public void mostrarTodosAlunos() {
		System.out.println("Listagem de todos alunos:");
		List<Pessoa> pessoas = this.repository.buscarTodos();
		
		pessoas.stream().filter(pessoa -> pessoa instanceof Aluno)
			.forEach(aluno -> System.out.println(aluno));
	}
	
	public String tipoPessoa(int id) {
		List<Pessoa> pessoas = this.repository.buscarTodos();
		String tipo = pessoas.stream().filter(pessoa -> pessoa.getId() == id)
				.findFirst().get().getClass().getSimpleName();
		
		return tipo;
	}
	
	public void atualizarDados(int id, String tipo) {		
		Pessoa pessoa = this.repository.buscarPorId(id);
		
		boolean continua = true;
		do {
			System.out.println("Dados atuais da pessoa/aluno selecionada:\n"
					+ pessoa + "\n"
					+ "Qual dado deseja atualizar?\n"
					+ "1 - Nome\n"
					+ "2 - Telefone\n"
					+ "3 - Data de nascimento");
			if (tipo.equals("Aluno")) {
				System.out.println("4 - Nota final do curso");
			}
			System.out.println("0 - Retornar ao Menu Principal");
			
			int entrada = sc.nextInt();
			if(entrada == 1) {
				System.out.println("Digite o nome: ");
				sc.nextLine();
				String nome = sc.nextLine();			
				pessoa.setNome(nome);
			}else if(entrada == 2) {
				System.out.println("Digite o telefone com DDD.\n"
						+ "Somente os 11 números (XXXXXXXXXXX): ");
				sc.nextLine();
				String telefone = sc.nextLine().replaceFirst("0", "");
				pessoa.setTelefone(telefone);
			}else if(entrada == 3) {
				System.out.println("Digite a data de nascimento (DD/MM/AAAA): ");
				sc.nextLine();
				String dataNascimento = sc.nextLine();
				pessoa.setDataNascimento(Data.stringParaData(dataNascimento));
			}else if (tipo.equals("Aluno") && entrada == 4) {
				System.out.println("Digite a nota final do curso: ");
				sc.nextLine();
				String notaFinal = sc.nextLine();
				pessoa.setNotaFinal(Double.parseDouble(notaFinal));
			}else if (entrada == 0) {	
				continua = false;
				break;
			}
			pessoa.setDataAlteracao(hoje);
			System.out.println("Atualizado com sucesso!");
		} while (continua == true);		
	}

	public void deletarPessoa(int opcao4) throws SistemaException {
		Pessoa pessoa = repository.buscarPorId(opcao4);
		
		if(pessoa == null ) {
			throw new SistemaException("Pessoa não encontrada!");
		}		
		repository.removerPorId(opcao4);
		
		System.out.println("Pessoa removida com sucesso!");		
	}
	
}