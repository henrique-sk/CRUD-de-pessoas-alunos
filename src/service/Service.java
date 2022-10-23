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
		this.repository.salvar(new Pessoa("Luana", "15777777777", Data.stringParaData("12/12/2002")));
		this.repository.salvar(new Aluno("Tamires", "42333333333", Data.stringParaData("10/10/1990"), 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", Data.stringParaData("02/02/1956"), 6.0));
	}

	public void criarCadastro() {
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
				|| telefone.length() > 11
				|| telefone.length() < 11) {
			System.out.println("Digite o telefone com DDD."
					+ "Somente os 11 números (###########): ");
			telefone = sc.nextLine().replaceFirst("0", "");
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
		String notaString = "";
		double notaDouble = -1;
		while (notaDouble == -1) {
			if (NotaFinal.isNumeric(notaString)
					&& (Double.parseDouble(notaString) >= 0
					&& Double.parseDouble(notaString) <= 10)) {
				notaDouble = Double.parseDouble(notaString);
			} else {
				System.out.println("Digite a nota final do curso: (entre 0 e 10)");
				notaString = sc.nextLine().replace(",", ".");
			}
		}
		return notaDouble;
	}

	public void listarCadastros() throws SistemaException {
		List<Pessoa> pessoas = this.repository.buscarTodos();

		Menu.MOSTRAR();
		int opcao = sc.nextInt();
		if (opcao < 0 || opcao > 3) {
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
		this.atualizarDados(this.receberId());
	}

	public void pesquisarPorNome() throws SistemaException {
		System.out.println("Digite o nome ou parte do nome da pessoa ou aluno(a): ");
		String fragmentoNome = sc.next().toLowerCase();

		List<Pessoa> pessoas = this.repository.buscarTodos().stream()
				.filter(pessoa -> pessoa.getNome().toLowerCase().contains(fragmentoNome)).collect(Collectors.toList());

		if (pessoas.size() > 1) {
			pessoas.forEach(pessoa -> System.out.println(pessoa));
			this.atualizarDados(this.receberId());
		} else if (pessoas.size() == 1) {
			this.atualizarDados(pessoas.get(0).getId());
		} else if (pessoas.isEmpty()) {
			throw new SistemaException(
					"Nenhuma ocorrência com '" + fragmentoNome + "' encontrada!\n"
							+ "Retornando ao Menu Principal!");
		}
	}

	public int receberId() throws SistemaException {
		System.out.println("Informe o ID para selecionar o cadastro correspondente "
				+ "(ou '0' para retornar ao menu principal):");
		int id = sc.nextInt();
		if (id == 0) {
			throw new SistemaException("Retornando ao Menu Principal!");
		}
		return id;
	}
	
	public void atualizarDados(int id) throws SistemaException {
		Pessoa pessoa = this.verificarIdRepository(id);

		int opcaoMenu;
		boolean continua = true;
		boolean virouAluno = false;
		while(continua == true) {
			Menu.ATUALIZAR(pessoa);
			opcaoMenu = sc.nextInt();
			sc.nextLine();
			if (opcaoMenu == 1) {
				pessoa.setNome(receberNome());
			} else if (opcaoMenu == 2) {
				pessoa.setTelefone(receberTelefone());
			} else if (opcaoMenu == 3) {
				pessoa.setDataNascimento(receberDataNascimento());
			} else if (opcaoMenu == 4) {
				if (pessoa instanceof Aluno) {
					Aluno aluno = (Aluno) pessoa;
					aluno.setNotaFinal(receberNotaFinal());
				} else {
					Aluno aluno = this.pessoaParaAluno(pessoa, this.receberNotaFinal());
					virouAluno = true;
					pessoa = aluno;
				}
			} else if (opcaoMenu == 5) {
				this.deletarPessoa(pessoa.getId());
				break;
			} else if (opcaoMenu == 0) {
				continua = false;
				throw new SistemaException("Retornando ao Menu Principal!");
			} else if (opcaoMenu < 0 || opcaoMenu > 5) {
				System.out.println("Opção inválida!!");
			}
			if (virouAluno == true) {
				System.out.println("Agora " + pessoa.getNome() + " é um(a) aluno(a)!");
				virouAluno = false;
			} else {
				System.out.println("Atualizado com sucesso!");				
			}
			pessoa.setDataAlteracao(new Date());			
		}
	}

	private Pessoa verificarIdRepository(int id) throws SistemaException {
		Pessoa pessoa = this.repository.buscarPorId(id);		
		if (pessoa == null) {
			throw new SistemaException("Cadastro não encontrado!");
		}
		return pessoa;
	}
	
	private Aluno pessoaParaAluno(Pessoa pessoa, double nota) {
		Pessoa aluno = new Aluno(pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento(), nota);
		this.repository.salvar(aluno);
		aluno.setDataCadastro(pessoa.getDataCadastro());
		this.repository.removerPorId(pessoa.getId());
		System.out.println("Novo(a) aluno(a) cadastrado(a) com sucesso!");
		return (Aluno) aluno;
	}

	private void deletarPessoa(int id) throws SistemaException {
		Pessoa pessoa = this.verificarIdRepository(id);

		boolean continua = true;
		while (continua == true) {
			System.out.println(
					"Tem certeza que deseja remover permanentemente o cadastro de " + pessoa.getNome() + "? (S/N)");
			String remover = sc.next().toUpperCase();
			if (remover.equals("N")) {
				System.out.println("Retornando ao Menu Principal!");
				continua = false;
			} else if (remover.equals("S")) {
				repository.removerPorId(id);
				System.out.println("Cadastro removido com sucesso!");
				continua = false;
			}
		}
	}

}