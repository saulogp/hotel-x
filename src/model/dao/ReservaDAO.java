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
import model.bean.Quarto;
import model.bean.Reserva;

/**
 *
 * @author saulo
 */
public class ReservaDAO {
    public void create(Reserva reserva) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO reserva(id, cod_quarto, cod_usuario, cod_cliente, check_in, check_out, comentarios, qtd_adulto, qtd_crianca) VALUES(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, null);
            stmt.setInt(2, reserva.getCodQuarto());
            stmt.setInt(3, reserva.getCodUsuario());
            stmt.setInt(4, reserva.getCodCliente());
            stmt.setString(5, reserva.getCheckIn());
            stmt.setString(6, reserva.getCheckOut());
            stmt.setString(7, reserva.getComentarios());
            stmt.setInt(8, reserva.getQtdAdulto());
            stmt.setInt(9, reserva.getQtdCriancas());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Reserva> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Reserva> rlist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM reserva");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                
                reserva.setId(rs.getInt("id"));
                reserva.setCodQuarto(rs.getInt("cod_quarto"));
                reserva.setCodUsuario(rs.getInt("cod_usuario"));
                reserva.setCodCliente(rs.getInt("cod_cliente"));
                reserva.setCheckIn(rs.getString("check_in"));
                reserva.setCheckOut(rs.getString("check_out"));
                reserva.setQtdAdulto(rs.getInt("qtd_adulto"));
                reserva.setQtdCriancas(rs.getInt("qtd_crianca"));
                reserva.setComentarios(rs.getString("comentarios"));
                
                rlist.add(reserva);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return rlist;
    }
    
    public List<Reserva> readForData(String check_in) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        ResultSet rs = null;

        List<Reserva> rlist = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM reserva WHERE cod_quarto LIKE ?");
            stmt.setString(1, "%"+check_in+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva();
                
                reserva.setId(rs.getInt("id"));
                reserva.setCodQuarto(rs.getInt("cod_quarto"));
                reserva.setCodUsuario(rs.getInt("cod_usuario"));
                reserva.setCodCliente(rs.getInt("cod_cliente"));
                reserva.setCheckIn(rs.getString("check_in"));
                reserva.setCheckOut(rs.getString("check_out"));
                reserva.setQtdAdulto(rs.getInt("qtd_adulto"));
                reserva.setQtdCriancas(rs.getInt("qtd_crianca"));
                reserva.setComentarios(rs.getString("comentarios"));
                
                rlist.add(reserva);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return rlist;
    }
    public void update(Reserva reserva) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE reserva SET cod_quarto = ?, cod_usuario = ?, cod_cliente = ?, check_in = ?, check_out = ?, comentarios = ?, qtd_adulto = ?, qtd_crianca = ? WHERE id = ?");
            
            stmt.setInt(1, reserva.getCodQuarto());
            stmt.setInt(2, reserva.getCodUsuario());
            stmt.setInt(3, reserva.getCodCliente());
            stmt.setString(4, reserva.getCheckIn());
            stmt.setString(5, reserva.getCheckOut());
            stmt.setString(6, reserva.getComentarios());
            stmt.setInt(7, reserva.getQtdAdulto());
            stmt.setInt(8, reserva.getQtdCriancas());
            stmt.setInt(9, reserva.getId());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean checkReserva(String checkIn, int codQaurto) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = true;

        try {
            stmt = con.prepareStatement("SELECT * FROM reserva WHERE check_in= ? and cod_quarto= ?");
            stmt.setString(1, checkIn);
            stmt.setInt(2, codQaurto);
            
            rs = stmt.executeQuery();

            if (rs.next()) {
                check = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;
    }
    
}
