package model;

public class Aluno extends Pessoa {
	
	public int notaFinal;

	public Aluno(String nome, int telefone, int dataNascimento, int dataCadastro, int dataAlteracao, int notaFinal) {
		super(nome, telefone, dataNascimento, dataCadastro, dataAlteracao);
		this.notaFinal = notaFinal;
	}

	public int getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}

//	@Override
//	public String toString() {
//		return "Aluno [notaFinal=" + notaFinal + "]";
//	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + this.nome + ", telefone=" + this.telefone + ", dataNascimento=" + this.dataNascimento
				+ ", dataCadastro=" + this.dataCadastro + ", dataAlteracao=" + this.dataAlteracao + ", notaFinal=" + this.notaFinal + "]";
	}
	
	

}
