# Unip221LPOO
Codigos de LPOO das aulas da Unip Swift 2022/1

# Programa Funcionario

O programa funcionário é feito como exemplo para treinar para a APS.

Este programa trata duas entidades e o relacionamento entre elas.

- Funcionario
  - Tem um Id único; 
  - Um Nome
- Departamento
  - Tem um Nome;
  - e uma Cidade
  - cada departamento é definido unicamente por seu nome e cidade
- Cadastro
  - um Funcionario pode trabalhar em zero ou mais departamentos
  - Um Departamento pode ter zero ou mais funcionários

Todos os dados do programa são persistentes e guardados em arquivos no forma csv:
- funcionarios.csv: uma coluna para o id e uma para o nome
- departamentos.csv: uma coluna para o nome e uma para a cidade
- funcionarios_departamentos.csv: uma tabela de relação entre funcionarios e departamentos, cada linha representa uma relação contendo o id de um funcionário e nome e cidade de um departamento.

A classe [Dao](programa_funcionario/src/model/dao/Dao.java) faz o trabalho de recuperar e salvar as informações.
