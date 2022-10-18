package model;

import java.util.Date;

import util.Data;
import util.Telefone;

public class Aluno extends Pessoa {
	
	Date hoje = new Date();

	public Aluno(String nome, String telefone, Date dataNascimento, Double notaFinal) {
		super(nome, telefone, dataNascimento);
		this.notaFinal = notaFinal;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
		this.matriculado = true;
	}

//	@Override
//	public String toString() {
//		return this.id
//				+ " - "	+ this.nome
//				+ " - Nota Final: " + String.format("%.2f", this.notaFinal)
//				+ " - Fone: " + Telefone.formataFone(this.telefone)
//				+ " - Nasc: " + Data.formataNascimento(this.dataNascimento)
//				+ " - Data do Cadastro: " + Data.formataData(this.dataCadastro)
//				+ " - Última Alteração: " + Data.formataData(this.dataAlteracao);
//	}	

}
