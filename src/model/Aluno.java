package model;

import util.Data;

public class Aluno extends Pessoa {
	
	public double notaFinal;

	public Aluno(String nome, String telefone, String dataNascimento, double notaFinal) {
		super(nome, telefone, dataNascimento
//				, dataCadastro, dataAlteracao
				);
		this.notaFinal = notaFinal;
		this.dataCadastro = Data.stringParaData("01/01/2020");
		this.dataAlteracao = Data.stringParaData("01/01/2020");
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
				+ " - Nasc: " + this.dataNascimento
				+ " - Cadastro: " + this.dataCadastro
				+ " - Última Alt.:" + this.dataAlteracao;
	}
	
	

}
