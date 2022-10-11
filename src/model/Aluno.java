package model;

import java.text.DateFormat;
import java.util.Date;

public class Aluno extends Pessoa {
	
	Date hoje = new Date();
	public double notaFinal;

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
		return this.id
				+ " - "	+ this.nome
				+ " - Nota Final: " + this.notaFinal
				+ " - Fone: "	+ this.telefone
				+ " - Nasc: " + DateFormat.getDateInstance(DateFormat.SHORT).format(this.dataNascimento)
				+ " - Cadastro: " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this.dataCadastro)
				+ " - Última Alt.:" + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this.dataAlteracao);
	}	

}
