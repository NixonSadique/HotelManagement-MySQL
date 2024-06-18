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
import model.Clientes;

/**
 *
 * @author HP
 */
public class ClientesDAO {
    
    public ArrayList<Clientes> recuperar(){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes";
        ArrayList<Clientes> lista = new ArrayList<>();
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getInt("Telefone")+"");
                cliente.setIdQuarto(rs.getInt("idQuarto"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            ConnectionManager.closeConnection(c, ps,rs);
        }
        return lista;
    }
    
    public boolean inserir(Clientes cliente){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO clientes(nome,email,telefone) VALUES (?,?,?)";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, Integer.parseInt(cliente.getTelefone()));
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
    
    public boolean atualizar(Clientes cliente) {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setInt(3, Integer.parseInt(cliente.getTelefone()));
            ps.setInt(4, cliente.getId());
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
    
    public boolean deletar(Clientes cliente) {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
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
    
    public boolean atribuirQuarto(Clientes cliente){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE clientes SET idQuarto = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, cliente.getIdQuarto());
            ps.setInt(2, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
    public Clientes procurar(int id){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Clientes cliente = null;
        try {
            ps = c.prepareCall(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getInt("telefone")+"");
                cliente.setIdQuarto(rs.getInt("idQuarto"));
            }
            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
