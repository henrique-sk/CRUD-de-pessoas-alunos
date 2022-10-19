package model;

import java.util.Date;

import util.Data;
import util.Telefone;

public class Aluno extends Pessoa {
	
	Date hoje = new Date();
	double notaFinal;

	public Aluno(String nome, String telefone, Date dataNascimento, double notaFinal) {
		super(nome, telefone, dataNascimento);
		this.notaFinal = notaFinal;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}

	@Override
	public String toString() {
		return super.toString()
				+ " - Nota Final: " + String.format("%.2f", this.notaFinal);
	}	

}
