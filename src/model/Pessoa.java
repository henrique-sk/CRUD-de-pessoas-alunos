package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import util.Contador;
import util.Data;
import util.Telefone;

public class Pessoa implements Banco {
	
	Date hoje = new Date();
	SimpleDateFormat sdf;
	public Integer id;
	public String nome;
	public String telefone;
	public Date dataNascimento;
	public Double notaFinal;
	public Date dataCadastro;
	public Date dataAlteracao;
	public boolean matriculado;
	
	public Pessoa(String nome, String telefone, Date dataNascimento) {
		this.id = Contador.proximoId();
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.notaFinal = null;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
		this.matriculado = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Double notaFinal) {
		this.notaFinal = notaFinal;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public boolean isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(boolean matriculado) {
		this.matriculado = matriculado;
	}

	@Override
	public String toString() {
		String infoMatricula = "";
		if (this.notaFinal != null) {
			infoMatricula = " - NotaFinal: " + this.notaFinal;
			if (this.matriculado) {
				infoMatricula += " - MATRICULADO";
				
			}
		}
		return this.id
				+ " - "	+ this.nome
				+ infoMatricula
				+ " - Fone: " + Telefone.formataFone(this.telefone)
				+ " - Nasc: " + Data.formataNascimento(this.dataNascimento)
				+ " - Data do Cadastro: " + Data.formataData(this.dataCadastro)
				+ " - Última Alteração: " + Data.formataData(this.dataAlteracao);
	}

}
