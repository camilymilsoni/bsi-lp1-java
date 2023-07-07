package br.com.projeto_2.dao;

import java.sql.*;
import br.com.projeto_2.dto.FornecedorDTO;
import java.text.SimpleDateFormat;

public class FornecedorDAO {

    public FornecedorDAO(){ //Método construtor da classe
    }
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    
    private ResultSet rs = null; //Atributo do tipo ResultSet utilizado para realizar consultas
    private Statement stmt = null; //Manipular o banco de dados
    
    public boolean inserirFornecedor(FornecedorDTO fornecedorDTO){ //Método utilizado para inserir um objeto fornecedorDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Instancia o Statement que será responsável por executar alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Insert into fornecedor (nome_for, cnpj_for, "
                    + "tel_for, data_cad_for ) values ( " 
                    + "'" + fornecedorDTO.getNome_for() + "', "
                    + "'" + fornecedorDTO.getCnpj_for() + "', "
                    + "'" + fornecedorDTO.getTel_for() + "', " 
                    + "to_date('" + data_format.format(fornecedorDTO.getData_cad_for()) + "','dd/mm/yyyy')) ";
            
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
    
    public boolean alterarFornecedor(FornecedorDTO fornecedorDTO){ //Método utilizado para alterar um objeto fornecedorDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Update fornecedor set "
                    + "nome_for = '" + fornecedorDTO.getNome_for() + "', "
                    + "cnpj_for = '" + fornecedorDTO.getCnpj_for() + "', "
                    + "tel_for = '" + fornecedorDTO.getTel_for() + "', "
                    + "data_cad_for = to_date('" + data_format.format(fornecedorDTO.getData_cad_for()) + "','dd/mm/yyyy') "
                    + "where id_for = " + fornecedorDTO.getId_for();
            
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
    
    public boolean excluirFornecedor(FornecedorDTO fornecedorDTO){ //Método utilizado para excluir um objeto fornecedorDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Delete from fornecedor where id_for = "
                    + fornecedorDTO.getId_for();
            
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
    
    public ResultSet consultarFornecedor(FornecedorDTO fornecedorDTO, int opcao){ //Método utilizado para consultar um objeto fornecedorDTO no banco de dados 
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados 
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            String comando = ""; //Comando SQL que será executado no banco de dados
            
            switch(opcao){
                case 1: 
                    comando = "Select f.id_for, f.nome_for " +
                            "from fornecedor f " +
                            "where f.nome_for ilike '" + fornecedorDTO.getNome_for() + "%' " +
                            "order by f.nome_for";
                break;    
                case 2:
                    comando = "Select f.nome_for, f.cnpj_for, f.tel_for, " +
                            "to_char(f.data_cad_for, 'dd/mm/yyyy') as data_cad_for " +
                            "from fornecedor f " +
                            "where f.id_for = " + fornecedorDTO.getId_for();
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
    
}
