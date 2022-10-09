package service;

import java.util.List;
import java.util.Scanner;

import model.Aluno;
import model.Pessoa;
import repository.Repository;

public class Service {
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
		String telefone = sc.nextLine();		
		System.out.println("Digite a data de nascimento (DD/MM/AAAA): ");
		String dataNascimento = sc.nextLine();		
		System.out.println("Digite a nota final do curso (ou 'N' para concluir o cadastro): ");			
		String notaFinal = sc.nextLine();
		
		Pessoa pessoa;
		if(notaFinal.equals("N")) {
			pessoa = new Pessoa(nome, telefone, dataNascimento);
		} else {			
			pessoa = new Aluno(nome, telefone, dataNascimento, Double.parseDouble(notaFinal));
		}		
		this.repository.salvar(pessoa);
		
		System.out.println("Cadastrado realizado com sucesso!");
	}
	
	public void mostrarTodasPessoas() {
		List<Pessoa> pessoas = this.repository.buscarTodos();
		
		pessoas.stream().forEach(pessoa -> System.out.println(pessoa));
	}
	
	public void mostrarTodosAlunos() {
		List<Pessoa> pessoas = this.repository.buscarTodos();
		
		pessoas.stream().filter(pessoa -> pessoa instanceof Aluno)
			.forEach(pessoa -> System.out.println(pessoa));
	}

}
