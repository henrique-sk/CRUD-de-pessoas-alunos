package model;

public class Aluno extends Pessoa {
	
	public double notaFinal;

	public Aluno(String nome, String telefone, String dataNascimento, String dataCadastro, String dataAlteracao, double notaFinal) {
		super(nome, telefone, dataNascimento, dataCadastro, dataAlteracao);
		this.notaFinal = notaFinal;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}

	@Override
	public String toString() {
		return "Aluno [ID=" + id + ", nome=" + this.nome + ", telefone=" + this.telefone + ", dataNascimento=" + this.dataNascimento
				+ ", dataCadastro=" + this.dataCadastro + ", dataAlteracao=" + this.dataAlteracao + ", notaFinal=" + this.notaFinal + "]";
	}
	
	

}
