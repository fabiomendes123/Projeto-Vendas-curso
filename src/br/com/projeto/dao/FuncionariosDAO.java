/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Funcionarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class FuncionariosDAO {
    
    //conexao
    private Connection con;
    
    public FuncionariosDAO(){
        this.con = new ConnectionFactory().getConnetion();
    }
    // metodo cadastrar
     public void cadastrarFuncionarios(Funcionarios obj){
        try{
            
            String sql = "insert into tb_funcionarios (nome,rg,cpf,email,senhas,cargo,nivel_acesso,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //passo conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt=con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
        // executar o comando sql
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
        
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro"+ erro);
        }
    
     } 
    
}
