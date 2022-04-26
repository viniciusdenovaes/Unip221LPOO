package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import controller.Controller;
import controller.Opcao;
import entidade.Cadastro;
import entidade.Departamento;
import entidade.Funcionario;

public class ViewTerminal implements View{

	@Override
	public Opcao menu() {
		try {
			
			System.out.println("Entre com uma das opcoes:");
			for(Opcao o: Opcao.values()) {
				System.out.println("" + o.codigo + " - " + o.descricao);
			}
			
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			try {
				String entrada = in.nextLine();
				int opcaoCodigo = Integer.parseInt(entrada);
				
				if(!Controller.opcoesByCodigos.keySet().contains(opcaoCodigo)) {
					throw new InputMismatchException("Opcao nao corresponde a nenhum codigo listado");
				}
				return Controller.opcoesByCodigos.get(opcaoCodigo);
				
			}catch(NumberFormatException e) {
				throw new InputMismatchException("Opcao entrada nao eh um numero inteiro");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			menu();
		}
		return null;
			
	}

	@Override
	public void listarFuncionarios(Cadastro cadastro) {
		System.out.println("Todos os Funcionarios cadastrados:");
		for(Funcionario f: cadastro.getFuncionarios()) {
			System.out.println(f);
		}
	}

	@Override
	public void listarDepartamentos(Cadastro cadastro) {
		System.out.println("Todos os Departamentos cadastrados:");
		for(Departamento d: cadastro.getDepartamentos()) {
			System.out.println(d);
		}
	}
	

	@Override
	public void listarFuncionariosFromDepartamento(Cadastro cadastro, Departamento departamento) {
		System.out.println("Todos os Funcionarios do departamento " + departamento + ":");
		for(Funcionario f: cadastro.getFuncionariosFromDepartamento(departamento)) {
			System.out.println(f);
		}
	}

	@Override
	public void listarDepartamentosFromFuncionario(Cadastro cadastro, Funcionario funcionario) {
		System.out.println("Todos os Funcionarios do funcionario " + funcionario + ":");
		for(Departamento d: cadastro.getDepartamentosFromFuncionario(funcionario.getId())) {
			System.out.println(d);
		}
	}



	@Override
	public Departamento addDepartamento() {
		return getNewDepartamento();
	}

	@Override
	public Funcionario addFuncionario() {
		return getNewFuncionario();
	}

	@Override
	public Departamento getDepartamentoFromLista(Cadastro cadastro) {
		System.out.println("Escolha um departamento");
		Departamento departamento = escolherDepartamento(cadastro);
		if(!cadastro.getDepartamentos().contains(departamento)) {
			System.out.println("Nao existe o departamento com as informacoes entradas");
			return null;
		}
		
		return departamento;
	}

	@Override
	public Funcionario getFuncionarioFromLista(Cadastro cadastro) {
		System.out.println("Entre com o id do funcionario");
		listarFuncionarios(cadastro);
		System.out.println("Entre com o id do funcionario");
		String id = getString();
		
		Funcionario funcionario = cadastro.getFuncionarioFromId(id);
		if(funcionario==null) {
			System.out.println("Nao existe o id deste funcionario");
		}
		
		return funcionario;
	}

	
	
	public Funcionario getNewFuncionario() {
		System.out.println("Entre com os dados do funcionario");
		String id = getIdFuncionario();
		String nome = getNomeFuncionario();
		return new Funcionario(id, nome);
	}
	
	public String getIdFuncionario() {
		System.out.println("Entre com o id do funcionario");
		return getString();
	}
	
	public String getNomeFuncionario() {
		System.out.println("Entre com a nome do funcionario");
		return getString();
	}
	
	
	
	public Departamento getNewDepartamento() {
		System.out.println("Entre com os dados do departamento");
		String nome = getNomeDepartamento();
		String cidade = getCidadeDepartamento();
		return new Departamento(nome, cidade);
	}
	
	public String getNomeDepartamento() {
		System.out.println("Entre com o nome do departamento");
		return getString();
	}
	
	public String getCidadeDepartamento() {
		System.out.println("Entre com a cidade do departamento");
		return getString();
	}
	
	private Departamento escolherDepartamento(Cadastro cadastro) {
		String nome = escolherNomesDepartamentos(cadastro);
		String cidade = escolherCidadesDepartamentos(cadastro);
		return new Departamento(nome, cidade);
	}
	
	private String escolherCidadesDepartamentos(Cadastro cadastro) {
		Set<String> cidades = new TreeSet<>();
		for(Departamento d: cadastro.getDepartamentos()) {
			cidades.add(d.getCidade());
		}
		System.out.println("Escolha uma das cidades");
		for(String cidade: cidades) {
			System.out.println(cidade);
		}
		System.out.println("Entre com a cidade escolhida:");
		return getString();
	}
	
	private String escolherNomesDepartamentos(Cadastro cadastro) {
		Set<String> nomes = new TreeSet<>();
		for(Departamento d: cadastro.getDepartamentos()) {
			nomes.add(d.getNome());
		}
		System.out.println("Escolha um dos nomes");
		for(String nome: nomes) {
			System.out.println(nome);
		}
		System.out.println("Entre com o nome escolhido:");
		return getString();
	}
	
	
	
	public String getString() {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		return str.trim();
	}
}
