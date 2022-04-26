package model.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import java.util.Set;

import entidade.Cadastro;
import entidade.Departamento;
import entidade.Funcionario;

public class Dao {
	
	private String funcionarioPath;
	private String departamentoPath;
	private String relacaoPath;
	
	private Cadastro cadastroInput;
	
	public Dao(String aFuncionarioPath, String aDepartamentoPath, String aRelacaoPath) {
		this.funcionarioPath = aFuncionarioPath;
		this.departamentoPath = aDepartamentoPath;
		this.relacaoPath = aRelacaoPath;
	}
    
	public Cadastro getCadastro(){
		
        this.cadastroInput = new Cadastro();
        
        loadFuncionarios();
        loadDepartamentos();
        
        return loadRelacoes();
    }

	private Cadastro loadRelacoes(){
		
        try (   InputStream is = new FileInputStream(this.relacaoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String idFuncionario = palavras[0];
                String nomeDepartamento = palavras[1];
                String cidadeDepartamento = palavras[2];

                Departamento departamento = new Departamento(nomeDepartamento, cidadeDepartamento);
                Funcionario funcionario = this.cadastroInput.getFuncionarioFromId(idFuncionario);
                
                cadastroInput.addRelacaoFuncionarioDepartamento(funcionario, departamento);

            }

        }catch(IOException e){
            e.printStackTrace();
        }
        
        return this.cadastroInput; 

    }

	private void loadFuncionarios(){

        try (   InputStream is = new FileInputStream(this.funcionarioPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String id = palavras[0];
                String nome = palavras[1];

                Funcionario funcionario = new Funcionario(id, nome);
                this.cadastroInput.addFuncionario(funcionario);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void loadDepartamentos(){

        try (   InputStream is = new FileInputStream(this.departamentoPath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
            ){
            String linha;
            int i=0;
            while((linha = br.readLine()) != null){

                String[] palavras = linha.split(",");

                String nome = palavras[0];
                String cidade = palavras[1];

                Departamento departamento = new Departamento(nome, cidade);
                this.cadastroInput.addDepartamento(departamento);

            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    
	
	
	
	public void saveCadastro(Cadastro cadastroOutput){
		
		saveFuncionarios(cadastroOutput.getFuncionarios());
		saveDepartamento(cadastroOutput.getDepartamentos());
		saveRelacoes(cadastroOutput);

    }

	private void saveRelacoes(Cadastro cadastroOutput){

        try(    OutputStream os = new FileOutputStream(this.relacaoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Funcionario funcionario: cadastroOutput.getFuncionarios()){
                for(Departamento departamento: cadastroOutput.getDepartamentosFromFuncionario(funcionario.getId())){
                    pw.println(funcionario.getId()+","+departamento.getNome()+","+departamento.getCidade());
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }

	private void saveFuncionarios(Set<Funcionario> funcionariosOutput){

        try(    OutputStream os = new FileOutputStream(this.funcionarioPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Funcionario funcionario: funcionariosOutput){
                pw.println(funcionario.getId()+","+funcionario.getNome());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }


	private void saveDepartamento(Set<Departamento> departamentoOutput){

        try(    OutputStream os = new FileOutputStream(this.departamentoPath);
                OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw, true);
                ){
            for(Departamento departamento: departamentoOutput){
                pw.println(departamento.getNome()+","+departamento.getCidade());
            }

        }catch(IOException e){
            e.printStackTrace();
        }

    }


}