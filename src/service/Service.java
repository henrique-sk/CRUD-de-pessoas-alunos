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
		this.repository.salvar(new Pessoa("PESSOA1", 987, "12/08/2020", "12/08/2020", "12/08/2020"));
		this.repository.salvar(new Aluno("ALUNO1", 987, "12/08/2020", "12/08/2020", "12/08/2020", 8765));
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
