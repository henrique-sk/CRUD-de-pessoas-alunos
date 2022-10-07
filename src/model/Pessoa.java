package model;

public class Pessoa {
	
	public String nome;
	public int telefone;
	public int dataNascimento;
	public int dataCadastro;
	public int dataAlteracao;
	
	public Pessoa(String nome, int telefone, int dataNascimento, int dataCadastro, int dataAlteracao) {
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
		this.dataAlteracao = dataAlteracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public int getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(int dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(int dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(int dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", telefone=" + telefone + ", dataNascimento=" + dataNascimento
				+ ", dataCadastro=" + dataCadastro + ", dataAlteracao=" + dataAlteracao + "]";
	}
	
	
	
}
