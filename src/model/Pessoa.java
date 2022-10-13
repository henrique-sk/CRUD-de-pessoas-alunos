package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.Contador;
import util.Data;

public class Pessoa implements Banco{
	
	Date hoje = new Date();
	SimpleDateFormat sdf;
	public Integer id;
	public String nome;
	public String telefone;
	public Date dataNascimento;
	public Date dataCadastro;
	public Date dataAlteracao;
	
	public Pessoa(String nome, String telefone, Date dataNascimento) {
		this.id = Contador.proximoId();
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = hoje;
		this.dataAlteracao = hoje;
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

	@Override
	public String toString() {
		return this.id
				+ " - "	+ this.nome
				+ " - Fone: " + this.telefone
				+ " - Nasc: " + DateFormat.getDateInstance(DateFormat.SHORT).format(this.dataNascimento)
				+ " - Cadastro: " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this.dataCadastro)
				+ " - Última Alt.:" + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this.dataAlteracao);
	}

	public void setNotaFinal(double notaFinal) {
		
	}

}
