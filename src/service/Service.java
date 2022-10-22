package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import exception.SistemaException;
import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Data;
import util.Menu;
import util.NotaFinal;

public class Service {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Repository<Pessoa> repository = new Repository<>();
	Scanner sc;
	
	public Service(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Pessoa("Luana", "15777777777",Data.stringParaData("12/12/2002")));
		this.repository.salvar(new Aluno("Tamires", "42333333333", Data.stringParaData("10/10/1990"), 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", Data.stringParaData("02/02/1956"), 6.0));
	}

	public void criarPessoaAluno() {
		System.out.println("Cadastramento:");
		String nome = receberNome();
		String telefone = receberTelefone();
		Date dataNascimento = receberDataNascimento();
		Pessoa pessoa = null;
		String temNota = "";
		while (temNota != "S") {
			temNota = sc.nextLine().toUpperCase();			
			if (temNota.equals("N")) {
				pessoa = new Pessoa(nome, telefone, dataNascimento);
				break;
			} else if (temNota.equals("S")) {
				Double notaFinal = receberNotaFinal();
				pessoa = new Aluno(nome, telefone, dataNascimento, notaFinal);
				break;
			}
			System.out.println("Deseja inserir uma nota final do curso? S/N");			
		}
		this.repository.salvar(pessoa);
		System.out.println("Cadastro realizado com sucesso!\nID n° " + pessoa);
	}

	private String receberNome() {
		System.out.println("Digite o nome: ");
		String nome = sc.nextLine();
		return nome;
	}

	private String receberTelefone() {
		String telefone = "";
		while (!telefone.matches("[0-9]+")
				|| telefone.length() > 11 || telefone.length() < 11) {
			System.out.println("Digite o telefone com DDD. "
				+ "Somente os 11 números (###########): ");
			telefone = sc.next().replaceFirst("0", "");
		}
		return telefone;
	}

	private Date receberDataNascimento() {
		String data = "";
		Date dataNascimento = null;
		boolean continua = true;
		while (continua == true)
			try {
				dataNascimento = sdf.parse(data);
				continua = false;
			} catch (Exception e) {
				System.out.println("Digite a data de nascimento conforme indicado (DD/MM/AAAA): ");
				data = sc.next();
			}		
		return dataNascimento;
	}

	private Double receberNotaFinal() {
		String notaFinal = "";
		double notaFinalOk = -1;
		while (notaFinalOk == -1) {
			if (NotaFinal.isNumeric(notaFinal)
					&& (Double.parseDouble(notaFinal) >= 0
					&& Double.parseDouble(notaFinal) <= 10)) {
				notaFinalOk = Double.parseDouble(notaFinal);
			} else {
				System.out.println("Digite a nota final do curso: (entre 0 e 10)");
				notaFinal = sc.nextLine().replace(",", ".");
			}
		}
		return notaFinalOk;
	}

	public void mostrarPessoasAlunos() throws SistemaException {
		List<Pessoa> pessoas = this.repository.buscarTodos();
		
		int opcao = sc.nextInt();					
		if (opcao == 0) {
			throw new SistemaException("Retornando ao Menu Principal!!");
		} else if (opcao < 0 || opcao > 3) {
			throw new SistemaException("Opção inválida!!");
		}		
		if (opcao != 0) {			
			System.out.println("Listagem:");
		}
		if (opcao == 1) {
			pessoas.stream().forEach(pessoa -> System.out.println(pessoa));
		} else if (opcao == 2) {
			pessoas.stream().filter(pessoa -> pessoa instanceof Aluno)
				.forEach(aluno -> System.out.println(aluno));
		} else if (opcao == 3) {
			pessoas.stream().filter(pessoa -> !(pessoa instanceof Aluno))
				.forEach(pessoa -> System.out.println(pessoa));
		}
		System.out.println("Informe o ID para selecionar o cadastro correspondente "
				+ "(ou '0' para retornar ao menu principal):");
	}
	
	public int pesquisarPorNome() throws SistemaException {		
		System.out.println("Digite o nome ou parte do nome da pessoa ou aluno(a): ");
		String fragmentoNome = sc.next().toLowerCase();
		
		List<Pessoa> verificaNome = this.repository.buscarTodos().stream()
				.filter(pessoa -> pessoa.getNome().toLowerCase().contains(fragmentoNome))
				.collect(Collectors.toList());
		
		int opcaoId = 0;
		if (verificaNome.size() > 1) {
			verificaNome.forEach(pessoa -> System.out.println(pessoa));
			System.out.println("Informe o ID do cadastro que deseja selecionar:");
			opcaoId = sc.nextInt();
		} else if (verificaNome.size() == 1) {
			opcaoId = verificaNome.get(0).getId();
		} else if (verificaNome.isEmpty()) {
			throw new SistemaException("Nenhuma ocorrência com '"
					+ fragmentoNome + "' encontrada!"
					+ "\nRetornando ao Menu Principal!");
		}
		return opcaoId;
	}

	private void pessoaParaAluno(Pessoa pessoa, double nota) {
		Pessoa aluno = new Aluno(pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento(), nota);
		this.repository.salvar(aluno);
		aluno.setDataCadastro(pessoa.getDataCadastro());
		this.repository.removerPorId(pessoa.getId());
		System.out.println("Novo(a) aluno(a) cadastrado(a) com sucesso!");
	}
	
	public void atualizarDados(int id) throws SistemaException {
		Pessoa pessoa = this.repository.buscarPorId(id);		
		
		if (pessoa == null) {
			throw new SistemaException("Pessoa não encontrada!");			
		}
		
		Menu.ATUALIZAR(pessoa);
		int entrada;
		entrada = sc.nextInt();
		sc.nextLine();
		if (entrada == 1) {
			pessoa.setNome(receberNome());
		} else if (entrada == 2) {
			pessoa.setTelefone(receberTelefone());
		} else if (entrada == 3) {
			pessoa.setDataNascimento(receberDataNascimento());
		} else if (entrada == 4) {
			if (pessoa instanceof Aluno) {
				Aluno aluno = (Aluno) pessoa;
				aluno.setNotaFinal(receberNotaFinal());
			} else {
				this.pessoaParaAluno(pessoa, this.receberNotaFinal());
			}
		} else if (entrada == 5) {
			this.deletarPessoa(id);
		}
		pessoa.setDataAlteracao(new Date());
		if (entrada >= 1 && entrada <= 4) {
			System.out.println("Cadastro atualizado com sucesso!!");			
		}
	}

	public void deletarPessoa(int opcaoId) throws SistemaException {
		Pessoa pessoa = repository.buscarPorId(opcaoId);

		if (pessoa == null) {			
			throw new SistemaException("Cadastro não encontrada!");
		}
		
		boolean continua = true;
		while (continua == true) {
			System.out.println("Tem certeza que deseja remover permanentemente o cadastro de " + pessoa.getNome() + "? (S/N)");
			String remover = sc.next().toUpperCase();
			if (remover.equals("N")) {
				System.out.println("Retornando ao Menu Principal!");
				continua = false;
			} else if (remover.equals("S")) {
				repository.removerPorId(opcaoId);
				System.out.println("Cadastro removido com sucesso!");
				continua = false;
			}
		}
	}

}