package br.com.projeto_1.dao;

import java.sql.*;
import br.com.projeto_1.dto.ClienteDTO;

public class ClienteDAO {

    public ClienteDAO(){ //Método construtor da classe
    }
    
    private ResultSet rs = null; //Atributo do tipo ResultSet utilizado para realizar consultas
    private Statement stmt = null; //Manipular o banco de dados
    
    public boolean inserirCliente(ClienteDTO clienteDTO){ //Método utilizado para inserir um objeto clienteDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Instancia o Statement que será responsável por executar alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Insert into cliente (nome_cli, logradouro_cli, numero_cli, bairro_cli, cidade_cli, "
                    + "estado_cli, cep_cli, cpf_cli, rg_cli) values ( " 
                    + "'" + clienteDTO.getNome_cli() + "', "
                    + "'" + clienteDTO.getLogradouro_cli() + "', "
                    + clienteDTO.getNumero_cli() + ", "
                    + "'" + clienteDTO.getBairro_cli() + "', " 
                    + "'" + clienteDTO.getCidade_cli() + "', " 
                    + "'" + clienteDTO.getEstado_cli() + "', "
                    + "'" + clienteDTO.getCep_cli() + "', " 
                    + "'" + clienteDTO.getCpf_cli() + "', " 
                    + "'" + clienteDTO.getRg_cli() + "') ";
            
            stmt.execute(comando.toUpperCase()); //Executa o comando SQL no banco de dados
            
            ConexaoDAO.con.commit(); //Da um commit no banco de dados
            
            stmt.close(); //Fecha o statement
            
            return true;
        } 
        catch(Exception e){
            System.out.println(e.getMessage()); //Caso tenha algum erro no código acima é enviado uma mensagem no console com o que está acontecendo
            return false;
        }
        finally{ //Independente de dar erro ou não ele vai fechar o banco de dados
            ConexaoDAO.CloseDB(); //Chama o método da classe ConexaoDAO para fechar o banco de dados
        }
    }
    
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){ //Método utilizado para consultar um objeto clienteDTO no banco de dados 
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados 
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            String comando = ""; //Comando SQL que será executado no banco de dados
            
            switch(opcao){
                case 1: 
                    comando = "Select c.* " +
                            "from cliente c " +
                            "where nome_cli like '" + clienteDTO.getNome_cli() + "%' " +
                            "order by c.nome_cli";
                break;    
                case 2:
                    comando = "Select c.* " +
                            "from cliente c " +
                            "where c.id_cli = " + clienteDTO.getId_cli();
                break;
                case 3:
                    comando = "Select c.id_cli, c.nome_cli " +
                            "from cliente c ";
                break; 
            }
            
            rs = stmt.executeQuery(comando.toUpperCase()); //Executa o comando SQL no banco de dados
            
            return rs; 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public boolean alterarCliente(ClienteDTO clienteDTO){ //Método utilizado para alterar um objeto clienteDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Update cliente set "
                    + "nome_cli = '" + clienteDTO.getNome_cli() + "', "
                    + "logradouro_cli = '" + clienteDTO.getLogradouro_cli() + "', "
                    + "numero_cli = " + clienteDTO.getNumero_cli() + ", "
                    + "bairro_cli = '" + clienteDTO.getBairro_cli() + "', "
                    + "cidade_cli = '" + clienteDTO.getCidade_cli() + "', "
                    + "estado_cli = '" + clienteDTO.getEstado_cli() + "', "
                    + "cep_cli = '" + clienteDTO.getCep_cli() + "', "
                    + "cpf_cli = '" + clienteDTO.getCpf_cli() + "', "
                    + "rg_cli = '" + clienteDTO.getRg_cli() + "' "
                    + "where id_cli = " + clienteDTO.getId_cli();
            
            stmt.execute(comando.toUpperCase()); //Executa o comando SQL no banco de dados
            
            ConexaoDAO.con.commit(); //Da um commit no banco de dados
            
            stmt.close(); //Fecha o statement
            
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB(); //Chama o método da classe ConexaoDAO para fechar o banco de dados
        }
    }
    
    public boolean excluirCliente(ClienteDTO clienteDTO){ //Método utilizado para excluir um objeto clienteDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Delete from cliente where id_cli = "
                    + clienteDTO.getId_cli();
            
            stmt.execute(comando); //Executa o comando SQL no banco de dados
            
            ConexaoDAO.con.commit(); //Da um commit no banco de dados
            
            stmt.close(); //Fecha o statement
            
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB(); //Chama o método da classe ConexaoDAO para fechar o banco de dados
        }
    }
    
}
