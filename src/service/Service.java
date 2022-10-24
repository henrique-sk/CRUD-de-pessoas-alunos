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
import util.Formatadores;
import util.Strings;

public class Service {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Repository<Pessoa> repository = new Repository<>();
	Scanner sc;

	public Service(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Pessoa("Luana", "15777777777", Formatadores.stringParaData("12/12/2002")));
		this.repository.salvar(new Aluno("Tamires", "42333333333", Formatadores.stringParaData("10/10/1990"), 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", Formatadores.stringParaData("02/02/1956"), 6.0));
	}

	public void criarCadastro() {
		System.out.println(Strings.CADASTRAMENTO.toString());
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
			System.out.println(Strings.TEM_NOTA.toString());
		}
		this.repository.salvar(pessoa);
		System.out.println(Strings.CADASTRADO.toString() + pessoa);
	}

	private String receberNome() {
		System.out.println(Strings.NOME.toString());
		String nome = sc.nextLine();
		return nome;
	}

	private String receberTelefone() {
		String telefone = "";
		while (!telefone.matches("[0-9]+")
				|| telefone.length() > 11
				|| telefone.length() < 11) {
			System.out.println(Strings.TELEFONE.toString());
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
				System.out.println(Strings.NASCIMENTO.toString());
				data = sc.next();
			}
		return dataNascimento;
	}

	private Double receberNotaFinal() {
		String notaString = "";
		double notaDouble = -1;
		while (notaDouble == -1) {
			if (Formatadores.isNumeric(notaString)
					&& (Double.parseDouble(notaString) >= 0
					&& Double.parseDouble(notaString) <= 10)) {
				notaDouble = Double.parseDouble(notaString);
			} else {
				System.out.println(Strings.NOTA.toString());
				notaString = sc.nextLine().replace(",", ".");
			}
		}
		return notaDouble;
	}

	public void listarCadastros() throws SistemaException {
		List<Pessoa> pessoas = this.repository.buscarTodos();

		System.out.println(Strings.MENU_CADASTROS.toString());
		int opcao = sc.nextInt();
		if (opcao < 0 || opcao > 3) {
			throw new SistemaException(Strings.OPCAO_INVALIDA.toString());
		} else if (opcao == 0) {
			throw new SistemaException(Strings.RETORNANDO_MENU.toString());
		}
		if (opcao != 0) {
			System.out.println(Strings.LISTAGEM.toString());
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
	
	public int receberId() throws SistemaException {
		System.out.println(Strings.INFORME_ID.toString());
		int id = sc.nextInt();
		if (id == 0) {
			throw new SistemaException(Strings.RETORNANDO_MENU.toString());
		}
		return id;
	}

	public void pesquisarPorNome() throws SistemaException {
		System.out.println(Strings.INFORME_NOME.toString());
		String fragmentoNome = sc.next().toLowerCase();

		List<Pessoa> pessoas = this.repository.buscarTodos().stream()
				.filter(pessoa -> pessoa.getNome().toLowerCase().contains(fragmentoNome)).collect(Collectors.toList());

		if (pessoas.size() > 1) {
			pessoas.forEach(pessoa -> System.out.println(pessoa));
			this.atualizarDados(this.receberId());
		} else if (pessoas.size() == 1) {
			this.atualizarDados(pessoas.get(0).getId());
		} else if (pessoas.isEmpty()) {
			throw new SistemaException(Strings.SEM_OCORRENCIA.toString()
					+ fragmentoNome	+ Strings.SEM_OCORRENCIA_2);
		}
	}
	
	public void atualizarDados(int id) throws SistemaException {
		Pessoa pessoa = this.verificarIdRepository(id);

		int opcaoMenu;
		boolean continua = true;
		boolean virouAluno = false;
		while(continua == true) {
			System.out.println(Strings.CABECALHO_ATUALIZAR.toString()
					+ pessoa
					+ Strings.OPCOES_ATUALIZAR);
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
				throw new SistemaException(Strings.RETORNANDO_MENU.toString());
			} else if (opcaoMenu < 0 || opcaoMenu > 5) {
				System.out.println(Strings.OPCAO_DESEJADA.toString());
			}
			if (virouAluno == true) {
				System.out.println(pessoa.getNome() + Strings.VIROU_ALUNO.toString());
				virouAluno = false;
			} else {
				System.out.println(Strings.ATUALIZADO.toString());				
			}
			pessoa.setDataAlteracao(new Date());			
		}

	}
	private Pessoa verificarIdRepository(int id) throws SistemaException {
		Pessoa pessoa = this.repository.buscarPorId(id);		
		if (pessoa == null) {
			throw new SistemaException(Strings.NAO_ENCONTRADO.toString());
		}
		return pessoa;
	}
	
	private Aluno pessoaParaAluno(Pessoa pessoa, double nota) {
		Pessoa aluno = new Aluno(pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento(), nota);
		this.repository.salvar(aluno);
		aluno.setDataCadastro(pessoa.getDataCadastro());
		this.repository.removerPorId(pessoa.getId());
		System.out.println(Strings.NOVO_ALUNO.toString());
		return (Aluno) aluno;
	}

	private void deletarPessoa(int id) throws SistemaException {
		Pessoa pessoa = this.verificarIdRepository(id);

		boolean continua = true;
		while (continua == true) {
			System.out.println(Strings.REMOVER_CADASTRO.toString()
					 + pessoa.getNome() + Strings.REMOVER_CADASTRO_2.toString());
			String remover = sc.next().toUpperCase();
			if (remover.equals("N")) {
				System.out.println(Strings.RETORNANDO_MENU.toString());
				continua = false;
			} else if (remover.equals("S")) {
				repository.removerPorId(id);
				System.out.println(Strings.REMOVIDO.toString());
				continua = false;
			}
		}
	}

}