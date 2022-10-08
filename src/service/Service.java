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
		this.repository.salvar(new Pessoa("Luana", "15777777777", "12/08/2020", "12/08/2020", "12/08/2020"));
		this.repository.salvar(new Pessoa("Roberto", "21444444444", "12/08/2020", "12/08/2020", "12/08/2020"));
		this.repository.salvar(new Aluno("Leonardo", "51888888888", "12/08/2020", "12/08/2020", "12/08/2020", 8.76));
		this.repository.salvar(new Aluno("Tamires", "42333333333", "12/08/2020", "12/08/2020", "12/08/2020", 9.65));
		this.repository.salvar(new Aluno("Lucas", "55222222222", "12/08/2020", "12/08/2020", "12/08/2020", 6));
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
