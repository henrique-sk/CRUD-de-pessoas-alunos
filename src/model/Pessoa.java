package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import util.Contador;
import util.Data;

public class Pessoa implements Banco{
	
	SimpleDateFormat sdf;
	public Integer id;
	public String nome;
	public long telefone;
	public Date dataNascimento;
	public Date dataCadastro;
	public Date dataAlteracao;
	
	public Pessoa(String nome, String telefone, String dataNascimento, String dataCadastro, String dataAlteracao) {
		this.id = Contador.proximoId();
		this.nome = nome;
		this.telefone = Long.parseLong(telefone);
		this.dataNascimento = Data.stringParaData(dataNascimento);
		this.dataCadastro = Data.stringParaData(dataCadastro);
		this.dataAlteracao = Data.stringParaData(dataAlteracao);
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

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
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
		return "Pessoa [ID=" + id + ", nome=" + nome + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento
				+ ", dataCadastro=" + dataCadastro + ", dataAlteracao=" + dataAlteracao + "]";
	}
	
}
