package br.com.projeto_2.ctr;

import java.sql.ResultSet;
import br.com.projeto_2.dto.FornecedorDTO;
import br.com.projeto_2.dto.ProdutoDTO;
import br.com.projeto_2.dao.ProdutoDAO;
import br.com.projeto_2.dao.ConexaoDAO;

public class ProdutoCTR {
    
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public ProdutoCTR(){ //Método construtor da classe
    }
    
    public String inserirProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO){
        try{
            if(produtoDAO.inserirProduto(produtoDTO, fornecedorDTO)){ 
                return "Produto cadastrado com sucesso!";
            }
            else{
                return "Produto NÃO cadastrado!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Produto NÃO cadastrado!";
        }
    }
    
    public String alterarProduto(ProdutoDTO produtoDTO, FornecedorDTO fornecedorDTO){
        try{
            if(produtoDAO.alterarProduto(produtoDTO, fornecedorDTO)){ 
                return "Produto alterado com sucesso!";
            }
            else{
                return "Produto NÃO alterado!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Produto NÃO alterado!";
        }
    }
    
    public String excluirProduto(ProdutoDTO produtoDTO){
        try{
            if(produtoDAO.excluirProduto(produtoDTO)){ 
                return "Produto excluído com sucesso!";
            }
            else{
                return "Produto NÃO excluído!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Produto NÃO excluído!";
        }
    }
    
    public ResultSet consultarProduto(ProdutoDTO produtoDTO, int opcao){ //Método utilizado para controlar o acesso ao método consultarProduto da classe ProdutoDAO
        
        ResultSet rs = null; //É criado um atributo do tipo ResultSet, pois esse método recebe o resultado de uma consulta
        
        rs = produtoDAO.consultarProduto(produtoDTO, opcao); //O atributo rs recebe a consulta realizada pelo método da classe DAO
        
        return rs;
        
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}
