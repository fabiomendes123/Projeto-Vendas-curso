/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.ItemVenda;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ItemVendaDAO {
 
     private Connection con;

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnetion();
        
    }
    // MERDOTO que cadastra item
    public void cadastrarItem(ItemVenda obj){
         try {
            String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal)values (?,?,?,?)";
            //conectar o banco de dados e organizar o comando sql
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setInt(1, obj.getVenda().getId());
                stmt.setInt(2, obj.getProduto().getId());
                stmt.setInt(3, obj.getQtd());
                stmt.setDouble(4, obj.getSubtotal());

                stmt.execute();
                stmt.close();

                
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        
        
        
        
    }
    // metodo que lista itens vendidos por um cliente ou ID
     public List<ItemVenda> listarItensPorVenda(int venda_id) {
        try {
            // 1o passo criara a lista
            List<ItemVenda> lista = new ArrayList<>();
            // 2 passpo criar o sql organizar e executar
            String sql = "SELECT i.id, p.descricao, i.qtd, p.preco, i.subtotal FROM tb_itensvendas as i\n "
                    + " inner join tb_produtos as p on (i.produto_id = p.id) where i.venda_id = ?;";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
              
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
             
                item.setId(rs.getInt("i.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                item.setProduto(prod);
                
                lista.add(item);
            }

            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro." + erro);
        }
        return null;
    }
    
}

