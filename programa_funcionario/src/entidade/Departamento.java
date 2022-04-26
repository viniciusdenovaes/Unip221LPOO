package entidade;

public class Departamento implements Comparable<Departamento>{
	
	private String nome;
	private String cidade;
	
	public Departamento(String nome, String cidade) {
		super();
		this.nome = nome;
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Departamento o) {
		if(!this.cidade.equals(o.cidade)) {
			return this.cidade.compareTo(o.cidade);
		}
		return this.nome.compareTo(o.nome);
	}
	

	@Override
	public String toString() {
		return "Departamento [nome=" + nome + ", cidade=" + cidade + "]";
	}


}
