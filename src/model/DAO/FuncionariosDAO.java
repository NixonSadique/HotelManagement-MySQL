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
import model.Funcionarios;

/**
 *
 * @author HP
 */
public class FuncionariosDAO {
    public ArrayList<Funcionarios> recuperar(){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM funcionarios";
        Funcionarios funcionario;
        ArrayList<Funcionarios> r = new ArrayList<>();
        
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                funcionario = new Funcionarios();
                funcionario.setCodigo(rs.getInt("ID_func"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setIdade(rs.getInt("idade"));
                funcionario.setGenero(rs.getString("genero"));
                r.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            ConnectionManager.closeConnection(c, ps, rs);
        }
        return r;
    }
    
    public boolean inserir(Funcionarios funcionario) {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO funcionarios(nome,senha,funcao,genero,idade) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSenha());
            ps.setString(3, funcionario.getFuncao());
            ps.setString(4, funcionario.getGenero());
            ps.setInt(5, funcionario.getIdade());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
    public boolean atualizar(Funcionarios funcionario) {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE funcionarios SET nome = ?, senha = ?, funcao = ?, genero = ?, idade =? WHERE ID_func ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSenha());
            ps.setString(3, funcionario.getFuncao());
            ps.setString(4, funcionario.getGenero());
            ps.setInt(5, funcionario.getIdade());
            ps.setInt(6, funcionario.getCodigo());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
    public boolean deletar(Funcionarios funcionario) {
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM funcionarios WHERE ID_func = ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, funcionario.getCodigo());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionManager.closeConnection(c, ps);
        }
    }
    
    public Funcionarios procurar(int id){
        Connection c = ConnectionManager.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM funcionarios WHERE ID_func = ?";
        Funcionarios funcionario = null;
        try {
            ps = c.prepareCall(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            while(rs.next()){
                funcionario = new Funcionarios();
                funcionario.setCodigo(rs.getInt("ID_func"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setIdade(rs.getInt("idade"));
                funcionario.setGenero(rs.getString("genero"));
            }
            return funcionario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
