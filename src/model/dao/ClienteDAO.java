/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

/**
 *
 * @author saulo
 */
public class ClienteDAO {
    public void create(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            stmt = con.prepareStatement("INSERT INTO cliente(id, nome, sobrenome, cpf, rg, sexo, endereco, num_casa, cep, cidade, uf, pais, email, telefone, celular) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, null);
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getSobrenome());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getRg());
            stmt.setString(6, cliente.getSexo());
            stmt.setString(7, cliente.getEndereco());
            stmt.setInt(8, cliente.getNumCasa());
            stmt.setString(9, cliente.getCep());
            stmt.setString(10, cliente.getCidade());
            stmt.setString(11, cliente.getUf());
            stmt.setString(12, cliente.getPais());
            stmt.setString(13, cliente.getEmail());
            stmt.setString(14, cliente.getTelefone());
            stmt.setString(15, cliente.getCelular());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Cliente> clist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT id, nome, sobrenome, cpf, rg, sexo, endereco, num_casa, cep, cidade, uf, pais, telefone, celular, email FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setSobrenome(rs.getString("sobrenome"));
                cli.setCpf(rs.getString("cpf"));
                cli.setRg(rs.getString("rg"));
                cli.setSexo(rs.getString("sexo"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumCasa(rs.getInt("num_casa"));
                cli.setCep(rs.getString("cep"));
                cli.setCidade(rs.getString("cidade"));
                cli.setUf(rs.getString("uf"));
                cli.setPais(rs.getString("pais"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setEmail(rs.getString("email"));
                clist.add(cli);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clist;
    }
    
    public void update(Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, sobrenome = ?, cpf = ?, rg = ?, sexo = ?, endereco = ?, num_casa = ?, cep = ?, cidade = ?, uf = ?, pais = ?, email = ?, telefone = ?, celular = ? WHERE id = ?");

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getSobrenome());
            stmt.setString(3, c.getCpf());
            stmt.setString(4, c.getRg());
            stmt.setString(5, c.getSexo());
            stmt.setString(6, c.getEndereco());
            stmt.setInt(7, c.getNumCasa());
            stmt.setString(8, c.getCep());
            stmt.setString(9, c.getCidade());
            stmt.setString(10, c.getUf());
            stmt.setString(11, c.getPais());
            stmt.setString(12, c.getEmail());
            stmt.setString(13, c.getTelefone());
            stmt.setString(14, c.getCelular());
            stmt.setInt(15, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> readForName(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Cliente> clist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setSobrenome(rs.getString("sobrenome"));
                cli.setCpf(rs.getString("cpf"));
                cli.setRg(rs.getString("rg"));
                cli.setSexo(rs.getString("sexo"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumCasa(rs.getInt("num_casa"));
                cli.setCep(rs.getString("cep"));
                cli.setCidade(rs.getString("cidade"));
                cli.setUf(rs.getString("uf"));
                cli.setPais(rs.getString("pais"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setEmail(rs.getString("email"));
                clist.add(cli);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clist;
    }
}
