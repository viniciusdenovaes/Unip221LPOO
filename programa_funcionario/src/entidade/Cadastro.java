package entidade;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Cadastro {
	
	Map<Funcionario, Set<Departamento>> funcionarios = new TreeMap<>();
	Map<Departamento, Set<Funcionario>> departamentos = new TreeMap<>();
	
	Map<String, Funcionario> funcionariosById = new TreeMap<>();
	
	public Funcionario getFuncionarioFromId(String id) {
		return funcionariosById.get(id);
	}
	
	public Set<Funcionario> getFuncionarios() {
		return funcionarios.keySet();
	}
	public void addFuncionario(Funcionario funcionario) {
		this.funcionarios.put(funcionario, new TreeSet<>());
		this.funcionariosById.put(funcionario.getId(), funcionario);
	}
	
	public Set<Departamento> getDepartamentos() {
		return departamentos.keySet();
	}
	public void addDepartamento(Departamento departamento) {
		this.departamentos.put(departamento, new TreeSet<>());
	}
	
	public Set<Departamento> getDepartamentosFromFuncionario(String id) {
		return funcionarios.get(new Funcionario(id, ""));
	}
	public Set<Funcionario> getFuncionariosFromDepartamento(Departamento departamento) {
		return departamentos.get(departamento);
	}
	
	public void addRelacaoFuncionarioDepartamento(Funcionario funcionario, Departamento departamento) {
		this.departamentos.get(departamento).add(funcionario);
		this.funcionarios.get(funcionario).add(departamento);
	}

}
