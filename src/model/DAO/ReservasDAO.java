/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import gestorHotel.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Reservas;

/**
 *
 * @author HP
 */
public class ReservasDAO {

    public boolean inserir(Reservas reserva) {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO reservas(nome,telefone,email,quarto,tempo,preco) VALUES(?,?,?,?,?,?)";
        
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, reserva.getNome());
            ps.setString(2, reserva.getTelefone());
            ps.setString(3, reserva.getEmail());
            ps.setInt(4, reserva.getQuarto());
            ps.setInt(5, reserva.getTempo());
            ps.setFloat(6, (float) reserva.getPreco());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
    public ArrayList<Reservas> recuperar() {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM reservas";
        Reservas reserva;
        ArrayList<Reservas> r = new ArrayList<>();

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                reserva = new Reservas();
                reserva.setId(rs.getInt("id"));
                reserva.setNome(rs.getString("nome"));
                reserva.setTelefone(rs.getString(3));
                reserva.setEmail(rs.getString("email"));
                reserva.setQuarto(rs.getInt("quarto"));
                reserva.setTempo(rs.getInt("tempo"));
                reserva.setPreco(rs.getFloat("preco"));
                r.add(reserva);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(c, ps, rs);
        }
        return r;
    }
    
    public boolean deletar(int id){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM reservas WHERE id = ?";
        
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally{
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
}
