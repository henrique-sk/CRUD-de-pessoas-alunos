package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.MaskFormatter;

import exception.SistemaException;
import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Data;
import util.Menu;
import util.NotaFinal;

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
		}		
		this.repository.salvar(pessoa);
		System.out.println("\\/ Cadastro realizado com sucesso! \\/\n"
				+ "Cadastro n° " + pessoa);
	}
	
	private String recebeNome() {
		System.out.println("Digite o nome: ");
		String nome = sc.next();
		return nome;
	}
	
	private String recebeTelefone() {		
		String telefone = "";
		while (!telefone.matches("[0-9]+") || telefone.length() < 11 || telefone.replaceFirst("0", "").length() > 11) {
			System.out.println("Digite o telefone com DDD. "
					+ "Somente os 11 números (XXXXXXXXXXX): ");
			telefone = sc.next();
		}
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
		String notaFinal = "";
		double notaFinalOk = -1;
		while (notaFinalOk == -1) {
			if (NotaFinal.isNumeric(notaFinal) &&
					(Double.parseDouble(notaFinal) >= 0 && Double.parseDouble(notaFinal) <= 10)) {
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
		
		if (opcao == 1) {
			System.out.println("Listagem de todas pessoas:");
			pessoas.stream().forEach(pessoa -> System.out.println(pessoa));			
		} else if (opcao == 2) {
			System.out.println("Listagem de todos alunos:");
			pessoas.stream().filter(pessoa -> pessoa instanceof Aluno)
			.forEach(aluno -> System.out.println(aluno));
		} else if (opcao == 3) {
			System.out.println("Listagem de todas pessoas que não são alunos:");
			pessoas.stream().filter(pessoa -> !(pessoa instanceof Aluno))
			.forEach(pessoa -> System.out.println(pessoa));
		}
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
			Menu.menuAtualizar(pessoa);
			int entrada = sc.nextInt();
			if(entrada == 1) {
				pessoa.setNome(recebeNome());
			}else if(entrada == 2) {
				pessoa.setTelefone(recebeTelefone());
			}else if(entrada == 3) {
				pessoa.setDataNascimento(recebeDataNascimento());
			}else if (entrada == 4) {
				if (tipo.equals("Aluno")) {
					pessoa.setNotaFinal(recebeNotaFinal());
				} else {
					this.pessoaParaAluno(pessoa, this.recebeNotaFinal());
					break;
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