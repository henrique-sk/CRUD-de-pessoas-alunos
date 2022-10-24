package Cadastros;


import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Formatadores;

public class Cadastros {
	Repository<Pessoa> repository;	
	
	public Cadastros(Repository<Pessoa> repository) {
		repository.salvar(new Pessoa("Luana Castro", "15777777777", Formatadores.stringParaData("12/12/2002")));
		repository.salvar(new Aluno("Tamires Carvalho", "42333333333", Formatadores.stringParaData("10/10/1990"), 9.65));
		repository.salvar(new Aluno("Lucas Flores", "55222222222", Formatadores.stringParaData("02/02/1956"), 6.0));
		repository.salvar(new Pessoa("Edison Arantes", "11999999999", Formatadores.stringParaData("12/01/2002")));
		repository.salvar(new Aluno("Thalia Jordano", "51444444444", Formatadores.stringParaData("15/02/1990"), 10));
		repository.salvar(new Aluno("Patricia Alvares", "53111111111", Formatadores.stringParaData("09/03/1956"), 9.0));
		repository.salvar(new Pessoa("Raul Dorneles", "15777777777", Formatadores.stringParaData("19/04/2002")));
		repository.salvar(new Aluno("Augusto Borges", "11999999999", Formatadores.stringParaData("12/05/1990"), 7.35));
		repository.salvar(new Aluno("Renata Paris", "55222222222", Formatadores.stringParaData("52/02/1956"), 6.0));
		repository.salvar(new Pessoa("Roger Melo", "11999999999", Formatadores.stringParaData("17/12/2002")));
		repository.salvar(new Aluno("Amélia Dias", "11999999999", Formatadores.stringParaData("12/10/1990"), 9.65));
		repository.salvar(new Aluno("Bruno Laerte", "55222222222", Formatadores.stringParaData("01/02/1956"), 6.0));
		repository.salvar(new Pessoa("Murilo Couto", "53111111111", Formatadores.stringParaData("12/12/2002")));
		repository.salvar(new Aluno("Bruna Louise", "42333333333", Formatadores.stringParaData("10/10/1990"), 9.65));
		repository.salvar(new Aluno("Santiago Melo", "55222222222", Formatadores.stringParaData("02/02/1956"), 6.0));
	}

}
