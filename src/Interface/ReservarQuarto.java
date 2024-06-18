/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interface;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.*;
import model.DAO.ClientesDAO;
import model.DAO.QuartoDAO;
import model.DAO.ReservasDAO;

/**
 *
 * @author HP
 */
public class ReservarQuarto extends javax.swing.JFrame {

    /**
     * Creates new form ReservarQuarto
     */
    
    
    
    public ReservarQuarto() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("IconHotel.png")));
        
        tabClientes();
        tabQuartos();
    }
    
    //tabela de clientes e de quartos aqui
    
    public void tabClientes(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
        ClientesDAO cDao = new ClientesDAO();
        Clientes cliente;
        ArrayList<Clientes> c = cDao.recuperar();
        
        for (int i = 0; i < c.size(); i++) {
            
            cliente = c.get(i);
            
            if(cliente.getIdQuarto() == 0){
                modelo.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getTelefone()
                });
            }
            
        }
    }
    
    public void tabQuartos(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaQuartos.getModel();
        Quarto quarto;
        QuartoDAO qDao = new QuartoDAO();
        ArrayList<Quarto> q = qDao.recuperar();
        
        for (int i = 0; i < q.size(); i++) {
            
            quarto = q.get(i);
            
            if(quarto.getDisponivel().equals("Disponivel")){
                modelo.addRow(new Object[]{
                    quarto.getId(),
                    quarto.getNrQuarto(),
                    quarto.getAndar(),
                    quarto.getDisponivel(),
                    quarto.getPreco()
                });
            }
        }

    }
    
    public Quarto getSelectedQuarto(){
        DefaultTableModel m = (DefaultTableModel) tabelaQuartos.getModel();
        QuartoDAO qDao = new QuartoDAO();
        ArrayList<Quarto> q = qDao.recuperar();
        int idTab = (int) m.getValueAt(tabelaQuartos.getSelectedRow(), 0);
        Quarto quarto = null;
        
        for (int i = 0; i < q.size(); i++) {
            quarto = q.get(i);
            
            if (quarto.getId() == idTab) {
                break;
            }
            
        }
        return quarto;
    }
    
    public Clientes getSelectedCliente(){
        DefaultTableModel m = (DefaultTableModel) tabelaClientes.getModel();
        ClientesDAO cDao = new ClientesDAO();
        ArrayList<Clientes> c = cDao.recuperar();
        int idTab = (int) m.getValueAt(tabelaClientes.getSelectedRow(), 0);
        Clientes cliente = null;
        
        for (int i = 0; i < c.size(); i++) {
            cliente = c.get(i);
            
            if (cliente.getId() == idTab) {
                break;
            }
            
        }
        return cliente;
    }
    
    void reservar(Clientes cliente, Quarto quarto) {
        //Organize this cod ffs, there's better way to do it now
        //create 2 functions to retrieve the selected id's, i believe that the code will become much more readable

        Reservas reserva = new Reservas();
        ReservasDAO rDao = new ReservasDAO();
        ClientesDAO cDao = new ClientesDAO();
        QuartoDAO qDao = new QuartoDAO();
        

        String estadia;
        
        estadia = JOptionPane.showInputDialog("Introduza o tempo de estadia:");

        cliente.setIdQuarto(quarto.getId());

        quarto.setClienteId(cliente.getId());
        quarto.setDisponivel("Indisponivel");
        quarto.setClienteNome(cliente.getNome());

        reserva.setNome(cliente.getNome());
        reserva.setTelefone(cliente.getTelefone());
        reserva.setEmail(cliente.getEmail());
        reserva.setQuarto(quarto.getNrQuarto());
        reserva.setTempo(Integer.parseInt(estadia));
        reserva.setPreco(quarto.getPreco());
        
        if (rDao.inserir(reserva) && qDao.atribuirCliente(quarto) && cDao.atribuirQuarto(cliente)) {
            JOptionPane.showMessageDialog(null, "Reserva efectuada!");
        }else{
            System.out.println("Erro!");
        }
    }
    
    public void removerC() {
        DefaultTableModel m = (DefaultTableModel) tabelaClientes.getModel();
        while (m.getRowCount() > 0) {
            m.removeRow(0);
        }
    }
    
    public void removerQ() {
        DefaultTableModel m = (DefaultTableModel) tabelaQuartos.getModel();
        while (m.getRowCount() > 0) {
            m.removeRow(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaQuartos = new javax.swing.JTable();
        buttonReserva = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reservar um Quarto");
        setResizable(false);

        tabelaClientes.setBackground(new java.awt.Color(204, 204, 255));
        tabelaClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaClientes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        tabelaClientes.setForeground(new java.awt.Color(0, 0, 0));
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Email", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaClientes);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clientes");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quarto");

        tabelaQuartos.setBackground(new java.awt.Color(204, 204, 255));
        tabelaQuartos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaQuartos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        tabelaQuartos.setForeground(new java.awt.Color(0, 0, 0));
        tabelaQuartos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Numero do Quarto", "Andar", "Disponibilidade", "Preco"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaQuartos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelaQuartos);

        buttonReserva.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        buttonReserva.setText("Reservar");
        buttonReserva.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(buttonReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReservaActionPerformed
        // TODO add your handling code here:
        //see if room is available 
        reservar(getSelectedCliente(),getSelectedQuarto());
        removerC();
        tabClientes();
        removerQ();
        tabQuartos();
    }//GEN-LAST:event_buttonReservaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservarQuarto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTable tabelaQuartos;
    // End of variables declaration//GEN-END:variables
}
