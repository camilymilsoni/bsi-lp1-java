package br.com.projeto_1.ctr;

import java.sql.ResultSet;
import br.com.projeto_1.dto.ClienteDTO;
import br.com.projeto_1.dao.ClienteDAO;
import br.com.projeto_1.dao.ConexaoDAO;

public class ClienteCTR {

    ClienteDAO clienteDAO = new ClienteDAO();
    
    public ClienteCTR(){ //Método construtor da classe
    }
    
    public String inserirCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.inserirCliente(clienteDTO)){ 
                return "Cliente cadastrado com sucesso!";
            }
            else{
                return "Cliente NÃO cadastrado!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Cliente NÃO cadastrado!";
        }
    }
    
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){ //Método utilizado para controlar o acesso ao método consultarCliente da classe ClienteDAO
        
        ResultSet rs = null; //É criado um atributo do tipo ResultSet, pois esse método recebe o resultado de uma consulta
        
        rs = clienteDAO.consultarCliente(clienteDTO, opcao); //O atributo rs recebe a consulta realizada pelo método da classe DAO
        
        return rs;
        
    }
    
    public void CloseDB(){
        ConexaoDAO.CloseDB();
    }
    
    public String alterarCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.alterarCliente(clienteDTO)){ 
                return "Cliente alterado com sucesso!";
            }
            else{
                return "Cliente NÃO alterado!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Cliente NÃO alterado!";
        }
    }
    
    public String excluirCliente(ClienteDTO clienteDTO){
        try{
            if(clienteDAO.excluirCliente(clienteDTO)){ 
                return "Cliente excluído com sucesso!";
            }
            else{
                return "Cliente NÃO excluído!";
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return "Cliente NÃO excluído!";
        }
    }
    
}
