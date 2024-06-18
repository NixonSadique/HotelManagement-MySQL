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
import model.Quarto;

/**
 *
 * @author HP
 */
public class QuartoDAO {
    
    public ArrayList<Quarto> recuperar(){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM quartos";
        ArrayList<Quarto> q = new ArrayList<>();
        
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Quarto quarto = new Quarto();
                quarto.setId(rs.getInt("id_quarto"));
                quarto.setNrQuarto(rs.getInt("num_quarto"));
                quarto.setPreco(rs.getFloat("preco"));
                quarto.setCategoria(rs.getString("categoria"));
                quarto.setAndar(rs.getString("andar")+"");
                quarto.setDisponivel(rs.getString("disponivel"));
                quarto.setClienteId(rs.getInt("idCliente"));
                quarto.setClienteNome(rs.getString("nomeCliente"));
                q.add(quarto);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
          ConnectionManager.closeConnection(c, ps, rs);
        }
        return q;
    }
    
    public boolean inserir(Quarto quarto){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO quartos(num_quarto,preco,categoria,andar,disponivel) VALUES (?,?,?,?,?)";
        
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1,quarto.getNrQuarto());
            ps.setFloat(2, (float) quarto.getPreco());
            ps.setString(3, quarto.getCategoria());
            ps.setString(4, quarto.getAndar());
            ps.setString(5, quarto.getDisponivel());
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
    
    public boolean atribuirCliente(Quarto quarto){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE quartos SET disponivel = ?, idCliente = ?, nomeCliente = ? WHERE id_quarto = ?";
        
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, quarto.getDisponivel());
            ps.setInt(2, quarto.getClienteId());
            ps.setString(3, quarto.getClienteNome());
            ps.setInt(4, quarto.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
    public boolean retirarCliente(Quarto quarto){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE quartos SET disponivel = ?, idCliente = ?, nomeCliente = ? WHERE id_quarto = ?";
        
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, quarto.getDisponivel());
            ps.setInt(2, quarto.getClienteId());
            ps.setString(3,quarto.getClienteNome());
            ps.setInt(4, quarto.getId());
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
