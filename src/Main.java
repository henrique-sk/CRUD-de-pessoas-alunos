import model.Aluno;
import model.Pessoa;

public class Main {

	public static void main(String[] args) {
		
		Pessoa pessoa = new Pessoa("João", 7474, 983274, 874, 43875);
		Pessoa aluno = new Aluno("João", 7474, 983274, 874, 43875, 872365);
		
		System.out.println(pessoa);
		System.out.println(aluno);
		
	}

}
