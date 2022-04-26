package entidade;

public class Funcionario implements Comparable<Funcionario>{
	
	private String id;
	private String nome;
	
	public Funcionario(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Funcionario o) {
		return this.id.compareTo(o.id);
	}

	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + "]";
	}


}
