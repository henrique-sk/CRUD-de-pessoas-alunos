package model;

import java.util.Date;

public class Aluno extends Pessoa {
	
	public int notaFinal;

	public Aluno(String nome, int telefone, String dataNascimento, String dataCadastro, String dataAlteracao, int notaFinal) {
		super(nome, telefone, dataNascimento, dataCadastro, dataAlteracao);
		this.notaFinal = notaFinal;
	}

	public int getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}

	@Override
	public String toString() {
		return "Aluno [ID=" + id + ", nome=" + this.nome + ", telefone=" + this.telefone + ", dataNascimento=" + this.dataNascimento
				+ ", dataCadastro=" + this.dataCadastro + ", dataAlteracao=" + this.dataAlteracao + ", notaFinal=" + this.notaFinal + "]";
	}
	
	

}
