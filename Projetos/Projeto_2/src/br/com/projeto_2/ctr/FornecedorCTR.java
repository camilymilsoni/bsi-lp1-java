package br.com.projeto_2.ctr;

import java.sql.ResultSet;
import br.com.projeto_2.dto.FornecedorDTO;
import br.com.projeto_2.dao.FornecedorDAO;
import br.com.projeto_2.dao.ConexaoDAO;

public class FornecedorCTR {

    FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    public FornecedorCTR(){ //Método construtor da classe
    }
    
    public String inserirFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if(fornecedorDAO.inserirFornecedor(fornecedorDTO)){ 
                return "Fornecedor cadastrado com sucesso!";
            }
            else{
                return "Fornecedor NÃO cadastrado!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO cadastrado!";
        }
    }
    
    public String alterarFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if(fornecedorDAO.alterarFornecedor(fornecedorDTO)){ 
                return "Fornecedor alterado com sucesso!";
            }
            else{
                return "Fornecedor NÃO alterado!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO alterado!";
        }
    }
    
    public String excluirFornecedor(FornecedorDTO fornecedorDTO){
        try{
            if(fornecedorDAO.excluirFornecedor(fornecedorDTO)){ 
                return "Fornecedor excluído com sucesso!";
            }
            else{
                return "Fornecedor NÃO excluído!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Fornecedor NÃO excluído!";
        }
    }
    
    public ResultSet consultarFornecedor(FornecedorDTO fornecedorDTO, int opcao){ //Método utilizado para controlar o acesso ao método consultarFornecedor da classe FornecedorDAO
        
        ResultSet rs = null; //É criado um atributo do tipo ResultSet, pois esse método recebe o resultado de uma consulta
        
        rs = fornecedorDAO.consultarFornecedor(fornecedorDTO, opcao); //O atributo rs recebe a consulta realizada pelo método da classe DAO
        
        return rs;
        
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
}
