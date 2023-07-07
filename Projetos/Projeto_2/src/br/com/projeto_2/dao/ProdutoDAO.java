package br.com.projeto_2.dao;

import java.sql.*;
import br.com.projeto_2.dto.FornecedorDTO;
import br.com.projeto_2.dto.ProdutoDTO;

public class ProdutoDAO {

    public ProdutoDAO(){
    }
    
    private ResultSet rs = null; //Atributo do tipo ResultSet utilizado para realizar consultas
    private Statement stmt = null; //Manipular o banco de dados
    
    public boolean inserirProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO){ //Método utilizado para inserir um objeto produtoDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Instancia o Statement que será responsável por executar alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Insert into produto (nome_prod, desc_prod, cod_bar_prod, "
                    + "p_custo_prod, p_venda_prod, id_for ) values ( " 
                    + "'" + produtoDTO.getNome_prod() + "', "
                    + "'" + produtoDTO.getDesc_prod() + "', "
                    + "'" + produtoDTO.getCod_bar_prod() + "', " 
                    + produtoDTO.getP_custo_prod() + ", "
                    + produtoDTO.getP_venda_prod() + ", "
                    + fornecedorDTO.getId_for() + ") ";
            
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
    
    public boolean alterarProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO){ //Método utilizado para alterar um objeto produtoDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Update produto set "
                    + "nome_prod = '" + produtoDTO.getNome_prod() + "', "
                    + "desc_prod = '" + produtoDTO.getDesc_prod() + "', "
                    + "cod_bar_prod = '" + produtoDTO.getCod_bar_prod() + "', "
                    + "p_custo_prod = " + produtoDTO.getP_custo_prod() + ", "
                    + "p_venda_prod = " + produtoDTO.getP_venda_prod() + ", "
                    + "id_for = " + fornecedorDTO.getId_for() + " "
                    + "where id_prod = " + produtoDTO.getId_prod();
            
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
    
    public boolean excluirProduto(ProdutoDTO produtoDTO){ //Método utilizado para excluir um objeto produtoDTO no banco de dados
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            //Comando SQL que será executado no banco de dados
            String comando = "Delete from produto where id_prod = "
                    + produtoDTO.getId_prod();
            
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
    
    public ResultSet consultarProduto(ProdutoDTO produtoDTO, int opcao){ //Método utilizado para consultar um objeto produtoDTO no banco de dados 
        try{
            ConexaoDAO.ConectDB(); //Chama o método que está na classe ConexaoDAO para abrir o banco de dados 
            
            stmt = ConexaoDAO.con.createStatement(); //Cria o statement que executa alguma coisa no banco de dados
            
            String comando = ""; //Comando SQL que será executado no banco de dados
            
            switch(opcao){
                case 1: 
                    comando = "Select p.* " +
                            "from produto p " +
                            "where p.nome_prod ilike '" + produtoDTO.getNome_prod() + "%' " +
                            "order by p.nome_prod";
                break;    
                case 2:
                    comando = "Select p. *, f.nome_for, f.id_for " +
                            "from produto p, fornecedor f " +
                            "where p.id_for = f.id_for and " +
                            "p.id_prod = " + produtoDTO.getId_prod();
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
