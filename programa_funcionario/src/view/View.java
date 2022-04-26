package view;

import controller.Opcao;
import entidade.Cadastro;
import entidade.Departamento;
import entidade.Funcionario;

public interface View {
	
	Opcao menu();
	
	void listarFuncionarios(Cadastro cadastro);
	void listarDepartamentos(Cadastro cadastro);
	
	void listarFuncionariosFromDepartamento(Cadastro cadastro, Departamento departamento);
	void listarDepartamentosFromFuncionario(Cadastro cadastro, Funcionario funcionario);
	
	Departamento addDepartamento();
	Funcionario addFuncionario();

	Departamento getDepartamentoFromLista(Cadastro cadastro);
	Funcionario getFuncionarioFromLista(Cadastro cadastro);
	
	
	
	
	

}
