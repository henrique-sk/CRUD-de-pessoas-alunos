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
import util.Menu;
import util.NotaFinal;

public class Service {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Repository<Pessoa> repository = new Repository<>();
	Scanner sc = new Scanner(System.in); // injetar?
	
	public Service() {		
		this.repository.salvar(new Pessoa("Luana", "15777777777", Data.stringParaData("12/12/2002")));
		this.repository.salvar(new Pessoa("Raul", "46444444444", Data.stringParaData("09/09/2002")));
		repository.buscarPorId(2).setNotaFinal(5.0);
		this.repository.salvar(new Aluno("Tamires", "42333333333", Data.stringParaData("10/10/1990"), 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", Data.stringParaData("02/02/1956"), 6.0));
	}

	public void criarPessoaAluno() {
		System.out.println("Cadastramento de pessoa/aluno.");
		String nome = receberNome();		
		String telefone = receberTelefone();
		Date dataNascimento = receberDataNascimento();
		Pessoa pessoa = null;
		boolean continua = true;
		while (continua == true) {
			System.out.println("Deseja inserir uma nota final do curso? S/N");
			String temNota = sc.nextLine().toUpperCase();
			if (temNota.equals("N")) {
				pessoa = new Pessoa(nome, telefone, dataNascimento);
				continua = false;
			} else if (temNota.equals("S")) {
				Double notaFinal = receberNotaFinal();
				pessoa = new Aluno(nome, telefone, dataNascimento, notaFinal);
				continua = false;
			}
		}
		this.repository.salvar(pessoa);
		System.out.println("Cadastro realizado com sucesso!\nID n° " + pessoa);
	}

	private String receberNome() {
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		return nome;
	}

	private String receberTelefone() {
		String telefone = "";
		while (!telefone.matches("[0-9]+") || telefone.length() < 11
				|| telefone.replaceFirst("0", "").length() > 11) {
			System.out.println("Digite o telefone com DDD. "
				+ "Somente os 11 números (###########): ");
			telefone = sc.next();
		}
		return telefone;
	}

	private Date receberDataNascimento() {
		Date dataNascimento = null;
		boolean continua = true;
		while (continua == true)
			try {
				dataNascimento = sdf.parse(sc.nextLine());
				continua = false;
			} catch (Exception e) {
				System.out.println("Digite a data de nascimento conforme indicado (DD/MM/AAAA): ");
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
				notaFinal = sc.next().replace(",", ".");
			}
		}
		return notaFinalOk;
	}

	public void mostrarPessoasAlunos(int opcao) {
		List<Pessoa> pessoas = this.repository.buscarTodos();

		System.out.println("Lista selecionada:");
		if (opcao == 1) {
			pessoas.stream().forEach(pessoa -> System.out.println(pessoa));
		} else if (opcao == 2) {
			pessoas.stream().filter(pessoa -> pessoa instanceof Aluno)
				.forEach(aluno -> System.out.println(aluno));
		} else if (opcao == 3) {
			pessoas.stream().filter(pessoa -> !(pessoa instanceof Aluno))
				.forEach(pessoa -> System.out.println(pessoa));
		}
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
		
		boolean continua = true;
		do {
			Menu.MENU_ATUALIZAR(pessoa);
			int entrada = sc.nextInt();
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
					break;
				}
			} else if (entrada == 0) {
				continua = false;
				break;
			}
			pessoa.setDataAlteracao(new Date());

		} while (continua == true);
	}

	public void deletarPessoa(int opcaoId) throws SistemaException {
		Pessoa pessoa = repository.buscarPorId(opcaoId);

		if (pessoa == null) {
			throw new SistemaException("Pessoa não encontrada!");
		}
		repository.removerPorId(opcaoId);

		System.out.println("Pessoa removida com sucesso!");
	}

}