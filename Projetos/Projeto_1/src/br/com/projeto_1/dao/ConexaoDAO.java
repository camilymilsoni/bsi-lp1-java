package br.com.projeto_1.dao;

import java.sql.*;

public class ConexaoDAO {

    public static Connection con = null; //Criando um atributo do tipo Connection que servirá para guardar a conexão com o banco de dados
    
    public ConexaoDAO(){ //Método construtor da classe
    }
    
    public static void ConectDB(){ //Método que abre a conexão com o banco de dados
        try{
            //Dados para conectar com o banco de dados Postgres
            String dsn = "projeto_1"; //Nome do banco de dados (igual ao criado no Postgres)
            String user = "postgres"; //Nome do usuário utilizado para se conectar
            String senha = "postdba"; //Senha do usuário acima informado
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/" + dsn;
            
            con = DriverManager.getConnection(url, user, senha);
            
            con.setAutoCommit(false);
            
            if(con == null){
                System.out.println("Erro ao abrir o banco");
            }
        }
        catch(Exception e){
            System.out.println("Problema ao abrir a base de dados! " + e.getMessage());
        }
    }
    
    public static void CloseDB(){ //Método que fecha a conexão com o banco de dados
        try{
            con.close();
        }
        catch(Exception e){
            System.out.println("Problema ao fechar a base de dados! " + e.getMessage());
        }
    }
    
}
