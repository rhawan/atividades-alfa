# atividades-alfa
Projeto Spring boot + ZK Framework realizando chamada para o backend de persistência através de RMI Java

O projeto é composto por 3 artefatos:
 * conta-bancaria-api - contém os VO's e interface compartilhada entre os projetos para que seja serializado e trafegado entre os projetos através do RMI Java
 * conta-bancaria-persistencia - Contém as classes servidor que recebem requisições RMI e persistem no banco de dados, utilizando Spring Data (JPA + Hibernate)
 * conta-bancaria-web - frontend implementado com framework ZK, enviando requisições para o projeto de persistência persistir, listar dados
 
 Os projetos foram criados no formato Spring Boot, que facilita sua criação, execução e deploy. Foram utilizados as seguintes API's: Hibernate, Spring, ZK, RMI
 
 Para executar o sistema, seguir os passos abaixo:
 1 - Configurar a porta RMI do servidor (arquivo application.properties do projeto conta-bancaria-persistencia), por padrão está 1099;<br />
 2 - Executar a classe ContaBancariaPersistenciaApplication.java como Java Project; <br />
 3 - Configurar o host e porta RMI do cliente (arquivo application.properties do projeto conta-bancaria-web), por padrão está 1099 e localhost; <br />
 4 - Executar a classe ContaBancariaWebApplication.java como Java Project; <br />
 5 - Acessar a página http://localhost:8080/pesquisaContaBancaria.zul; <br />
 6 - No menu superior é possível alternar entre as funcionalidades do sistema: Cadastro de Conta Bancária e Movimentação; <br />
