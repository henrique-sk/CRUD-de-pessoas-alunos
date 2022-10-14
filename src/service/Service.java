package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Data;

public class Service {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
	Date hoje = new Date();
	Scanner sc;
	Repository<Pessoa> repository = new Repository<>();
	
	public Service(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Pessoa("Luana", "15777777777", Data.stringParaData("12/12/2002")));
		this.repository.salvar(new Aluno("Tamires", "42333333333", Data.stringParaData("10/10/1990"), 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", Data.stringParaData("02/02/1956"), 6));
	}	
	
	public void criarPessoaAluno() {
		String nome = recebeNome();
		String telefone = recebeTelefone();
		Date dataNascimento = recebeDataNascimento();		
		Pessoa pessoa = null;
		boolean continua = true;
		while (continua == true) {
			System.out.println("Deseja inserir uma nota final do curso? S/N");
			String temNota = sc.nextLine().toUpperCase();
			if (temNota.equals("N")) {
				pessoa = new Pessoa(nome, telefone, dataNascimento);
				continua = false;
			} else if (temNota.equals("S")){
				Double notaFinal = recebeNotaFinal();
				pessoa = new Aluno(nome, telefone, dataNascimento, notaFinal);
				continua = false;
			}
			this.repository.salvar(pessoa);
		}		
		System.out.println("\\/ Cadastro realizado com sucesso! \\/\n"
				+ "Cadastro n° " + pessoa);
	}
	
	private String recebeNome() {
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		return nome;
	}
	
	private String recebeTelefone() {
		System.out.println("Digite o telefone com DDD. "
				+ "Somente os 11 números (XXXXXXXXXXX): ");
		String telefone = sc.next().replaceFirst("^0+(?!$)", "").replaceAll("[^0-9 ]", "");
		return telefone;
	}
	
	private Date recebeDataNascimento() {
		Date dataNascimento = null;
		boolean continua = true;
		while (continua == true)
			try {
				dataNascimento = sdf.parse(sc.nextLine());
				continua = false;
			} catch (Exception e) {
				continua = true;
				System.out.println("Digite a data de nascimento conforme indicado (DD/MM/AAAA): ");
			}
		return dataNascimento;
	}
	
	private Double recebeNotaFinal() {
		System.out.println("Digite a nota final do curso: ");
		Double notaFinal = sc.nextDouble();
		return notaFinal;
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
	
	private void pessoaParaAluno(Pessoa pessoa, double nota) {		
		Pessoa aluno = new Aluno(pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento(), nota);
		this.repository.salvar(aluno);
		aluno.setDataCadastro(pessoa.getDataCadastro());
		this.repository.removerPorId(pessoa.getId());
		System.out.println("Novo(a) aluno(a) cadastrado(a) com sucesso!");
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
					+ "3 - Data de nascimento\n"
					+ "4 - Inserir/alterar nota final do curso\n"
					+ "0 - Retornar ao Menu Principal");
			int entrada = sc.nextInt();
			if(entrada == 1) {
				pessoa.setNome(recebeNome());
			}else if(entrada == 2) {
				pessoa.setTelefone(recebeTelefone());
			}else if(entrada == 3) {
				pessoa.setDataNascimento(recebeDataNascimento());
			}else if (entrada == 4) {
				if (!tipo.equals("Aluno")) {
					this.pessoaParaAluno(pessoa, this.recebeNotaFinal());
					break;
				} else {
					pessoa.setNotaFinal(recebeNotaFinal());
				}				
			}else if (entrada == 0) {
				continua = false;
				break;
			}
			pessoa.setDataAlteracao(hoje);			
			
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