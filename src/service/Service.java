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
	
	private Date recebeDataNascimento() {
		Date dataNascimento = null;
		boolean continua2 = true;
		while (continua2 == true)
			try {
				dataNascimento = sdf.parse(sc.nextLine());
				continua2 = false;
			} catch (Exception e) {
				continua2 = true;
				System.out.println("Digite a data de nascimento conforme indicado (DD/MM/AAAA): ");
			}
		return dataNascimento;
	}
	
	public void criarPessoaAluno() {
		System.out.println("Digite o nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite o telefone com DDD. "
				+ "Somente os 11 números (XXXXXXXXXXX): ");
		String telefone = sc.next().replaceFirst("^0+(?!$)", "").replaceAll("[^0-9 ]", "");
		
		Date dataNascimento = recebeDataNascimento();
//		boolean continua2 = true;
//		Date dataNascimento = null;
//		while (continua2 == true) {
//			String dataNascimentoString = sc.nextLine();
//			try {
//				dataNascimento = sdf.parse(dataNascimentoString);
//				continua2 = false;
//			} catch (Exception e) {
//				continua2 = true;
//				System.out.println("Digite a data de nascimento conforme indicado (DD/MM/AAAA): ");
//			}	
//		}		
		
		Pessoa pessoa;
		boolean continua3 = true;
		while (continua3 == true) {
			System.out.println("Deseja inserir uma nota final do curso? S/N");
			String temNota = sc.nextLine().toUpperCase();
			if (temNota.equals("N")) {
				pessoa = new Pessoa(nome, telefone, dataNascimento);
				this.repository.salvar(pessoa);
				continua3 = false;
			} else if (temNota.equals("S")){
				System.out.println("Qual a nota final do curso?");
				Double notaFinal = sc.nextDouble();
				pessoa = new Aluno(nome, telefone, dataNascimento, notaFinal);
				this.repository.salvar(pessoa);
				continua3 = false;
			} 
		}
//		this.repository.salvar(pessoa);
		
//		System.out.println("Digite a nota final do curso (ou 'N' para concluir o cadastro): ");			
//		String notaFinal = sc.nextLine();
//		
//		Pessoa pessoa;
//		
//		if(notaFinal.toUpperCase().equals("N")) {
//			pessoa = new Pessoa(nome, telefone, dataNascimento);
//		} else {			
//			pessoa = new Aluno(nome, telefone, dataNascimento, Double.parseDouble(notaFinal));
//		}		
//		this.repository.salvar(pessoa);
		
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
					+ "Qual dado deseja atualizar? (Ou '0' para retornar ao Menu Principal)\n"
					+ "1 - Nome\n"
					+ "2 - Telefone\n"
					+ "3 - Data de nascimento");
			if (tipo.equals("Aluno")) {
				System.out.println("4 - Nota final do curso");
			}
			
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
				String telefone = sc.nextLine().replaceFirst("^0+(?!$)", "").replaceAll("[^0-9 ]", "");
				pessoa.setTelefone(telefone);
			}else if(entrada == 3) {
				pessoa.setDataNascimento(recebeDataNascimento());
//				boolean continua2 = true;
//				while (continua2 == true) {
//					String dataNascimento = sc.nextLine();
//					Date nascimento = null;
//					try {
//						nascimento = sdf.parse(dataNascimento);
//						continua2 = false;
//					} catch (Exception e) {
//						continua2 = true;
//						System.out.println("Digite a data de nascimento conforme indicado (DD/MM/AAAA): ");
//					}					
//					pessoa.setDataNascimento(nascimento);
//				}
			}else if (tipo.equals("Aluno") && entrada == 4) {
				System.out.println("Digite a nota final do curso: ");
				sc.nextLine();
				Double notaFinal = sc.nextDouble();
				pessoa.setNotaFinal(notaFinal);
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