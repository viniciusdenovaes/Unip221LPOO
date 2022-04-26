package teste;

import controller.Controller;
import model.dao.Dao;
import view.View;
import view.ViewTerminal;

class Main {
	
	public static void main(String[] args) {
		View view = new ViewTerminal();
		String funcionarioPath = "files/funcionarios.csv";
		String departamentoPath = "files/departamentos.csv";
		String relacaoPath = "files/funcionarios_departamentos.csv";
		Dao model = new Dao(funcionarioPath, departamentoPath, relacaoPath);
		
		new Controller(view, model).init();
	}

}
