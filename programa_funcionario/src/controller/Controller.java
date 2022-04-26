package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import entidade.Cadastro;
import entidade.Departamento;
import entidade.Funcionario;
import model.dao.Dao;
import view.View;

public class Controller {
	
	private View view;
	private Dao model;
	private Cadastro cadastro;
	
	public static Map<Integer, Opcao> opcoesByCodigos = new TreeMap<>();
	{
		for(Opcao o: Opcao.values()) {
			opcoesByCodigos.put(o.codigo, o);
		}
	}
	
	public Controller(View view, Dao model) {
		this.view = view;
		this.model = model;
	}
	
	public void init() {
		cadastro = model.getCadastro();
		
		Opcao codigo = null;
		while(codigo!=Opcao.SAIR) {
			codigo = view.menu();
			switch (codigo) {
			case LISTA_FUNCIONARIOS: {listarFuncionarios(); break;}
			case LISTA_DEPARTAMENTOS: {listarDepartamentos(); break;}
			case ADICIONA_FUNCIONARIO: {adicionarFuncionario(); break;}
			case ADICIONA_DEPARTAMENTO: {adicionarDepartamento(); break;}
			case LISTA_FUNCIONARIOS_FROM_DEPARTAMENTO: {listaFuncionariosFromDepartamento(); break;}
			case LISTA_DEPARTAMENTOS_FROM_FUNCIONARIO: {listaDepartamentosFromFuncionario(); break;}
			case ADICIONA_RELACAO: {addRelacao(); break;}
			case SAIR: {terminar(); break;}
			default:
				throw new IllegalArgumentException("Unexpected value: " + codigo);
			}
		}
	}
	
	private void listarFuncionarios() {
		view.listarFuncionarios(cadastro);
	}
	
	private void listarDepartamentos() {
		view.listarDepartamentos(cadastro);
	}
	
	private void adicionarFuncionario() {
		Funcionario funcionario = view.addFuncionario();
		this.cadastro.addFuncionario(funcionario);
	}
	
	private void adicionarDepartamento() {
		Departamento departamento = view.addDepartamento();
		this.cadastro.addDepartamento(departamento);
	}
	
	private void listaFuncionariosFromDepartamento() {
		Departamento departamento = view.getDepartamentoFromLista(cadastro);
		if(departamento==null) return;
		view.listarFuncionariosFromDepartamento(cadastro, departamento);
	}
	
	
	private void listaDepartamentosFromFuncionario() {
		Funcionario funcionario = view.getFuncionarioFromLista(cadastro);
		if(funcionario==null) return;
		view.listarDepartamentosFromFuncionario(cadastro, funcionario);
	}
	
	private void addRelacao() {
		Funcionario funcionario = view.getFuncionarioFromLista(cadastro);
		if(funcionario==null) return;
		Departamento departamento = view.getDepartamentoFromLista(cadastro);
		if(departamento==null) return;
		cadastro.addRelacaoFuncionarioDepartamento(funcionario, departamento);
		
	}
	
	private void terminar() {
		model.saveCadastro(cadastro);
	}
	
	
	
}
