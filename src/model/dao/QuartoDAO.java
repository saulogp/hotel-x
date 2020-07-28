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
import model.bean.Quarto;

/**
 *
 * @author saulo
 */
public class QuartoDAO {
    public void create(Quarto quarto){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try { 
            stmt = con.prepareStatement("INSERT INTO quarto(id, tipo, descricao, numero, ativo, valor, qtd_pessoas, num_cama) VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, null);
            stmt.setString(2, quarto.getTipo());
            stmt.setString(3, quarto.getDescricao());
            stmt.setInt(4, quarto.getNumero());
            stmt.setString(5, quarto.getAtivo());
            stmt.setDouble(6, quarto.getValor());
            stmt.setInt(7, quarto.getQtd_pessoas());
            stmt.setInt(8, quarto.getNum_cama());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Quarto> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Quarto> qlist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT id, tipo, numero, valor, ativo, qtd_pessoas, num_cama, descricao FROM quarto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto qua = new Quarto();
                
                qua.setId(rs.getInt("ID"));
                qua.setTipo(rs.getString("Tipo"));
                qua.setNumero(rs.getInt("Numero"));
                qua.setValor(rs.getDouble("Valor"));
                qua.setAtivo(rs.getString("ativo"));
                qua.setQtd_pessoas(rs.getInt("qtd_pessoas"));
                qua.setNum_cama(rs.getInt("num_cama"));
                qua.setDescricao(rs.getString("descricao"));
                qlist.add(qua);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return qlist;
    }
    public List<Quarto> readForType(String tipo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Quarto> qlist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM quarto WHERE tipo LIKE ?");
            stmt.setString(1, "%"+tipo+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Quarto qua = new Quarto();
                
                qua.setId(rs.getInt("ID"));
                qua.setTipo(rs.getString("Tipo"));
                qua.setNumero(rs.getInt("Numero"));
                qua.setValor(rs.getDouble("Valor"));
                qua.setAtivo(rs.getString("ativo"));
                qua.setQtd_pessoas(rs.getInt("qtd_pessoas"));
                qua.setNum_cama(rs.getInt("num_cama"));
                qua.setDescricao(rs.getString("descricao"));
                qlist.add(qua);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return qlist;
    }
    
    public void update(Quarto quarto) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE quarto SET tipo=?, descricao=?, numero=?, ativo=?, valor=?, qtd_pessoas=?, num_cama=? WHERE id = ?");

            stmt.setString(1, quarto.getTipo());
            stmt.setString(2, quarto.getDescricao());
            stmt.setInt(3, quarto.getNumero());
            stmt.setString(4, quarto.getAtivo());
            stmt.setDouble(5, quarto.getValor());
            stmt.setInt(6, quarto.getQtd_pessoas());
            stmt.setInt(7, quarto.getNum_cama());
            stmt.setInt(8, quarto.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
