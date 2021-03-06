/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.valdir.modelView;

import br.valdir.log.ArquivoLogBD;
import br.valdir.modelBeans.ModelTable;
import br.valdir.modelConection.ConexaoMySqlExterna;
import br.valdir.modelDao.DaoModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Valdir
 */
public class ExibeSalva extends javax.swing.JFrame {

    ConexaoMySqlExterna conex = new ConexaoMySqlExterna();
    DaoModel dao = new DaoModel();
    BarProgress bar = new BarProgress();

    public ExibeSalva(String usuario) {
        initComponents();
        bar.setVisible(false);
        preencherTabela("SELECT * FROM kit_atleta  ");
        String usu = usuario;
        if (usu.equals("Admin")) {
            jbtBaixar.setEnabled(true);
        }
    }

    private ExibeSalva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtOrdenar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAgenda = new javax.swing.JTable();
        jbtBaixar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(null);

        jbtOrdenar.setText("Ordenar");
        jbtOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrdenarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtOrdenar);
        jbtOrdenar.setBounds(550, 450, 120, 23);

        jTableAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableAgenda);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 910, 402);

        jbtBaixar.setText("Baixar DB");
        jbtBaixar.setEnabled(false);
        jbtBaixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBaixarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtBaixar);
        jbtBaixar.setBounds(260, 450, 120, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/valdir/img/background-1808894_960_720.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 950, 500);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(966, 560));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrdenarActionPerformed
        bar.setVisible(true);
        preencherTabela("SELECT * FROM kit_atleta utf8 order by nome ");

    }//GEN-LAST:event_jbtOrdenarActionPerformed

    private void jbtBaixarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBaixarActionPerformed
        bar.setVisible(true);
        new ArquivoLogBD(new Date() + "...Iniciando conexao com banco externo e salvando banco local");
        BaixarBD("SELECT * FROM kit_atleta utf8 order by nome ");
    }//GEN-LAST:event_jbtBaixarActionPerformed

    public void preencherTabela(String Sql) {
        new Thread() {
            public void run() {
                ArrayList dados = new ArrayList();
                String[] colunas = new String[]{"ID", "Nome", "cpf", "rg", "sexo", "telefone", "email", "Nascimento"};
                conex.conexao();
                conex.ExecutaSql(Sql);

                try {
                    conex.rs.first();

                    do {

                        dados.add(new Object[]{conex.rs.getInt("idkit_atleta"), conex.rs.getString("nome"), conex.rs.getString("cpf"), conex.rs.getString("rg"), conex.rs.getString("sexo"), conex.rs.getString("telefone"), conex.rs.getString("email"), conex.rs.getString("data_nasc")});
                        List<String> list = new ArrayList();
                        list.add(Integer.toString(conex.rs.getInt("idkit_atleta")));
                        list.add(conex.rs.getString("nome"));
                        list.add(conex.rs.getString("cpf"));
                        list.add(conex.rs.getString("rg"));
                        list.add(conex.rs.getString("sexo"));
                        list.add(conex.rs.getString("telefone"));
                        list.add(conex.rs.getString("email"));
                        list.add(conex.rs.getString("data_nasc"));

                        // dao.Adiciona(list);
                    } while (conex.rs.next());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "erro ao carregar" + ex);
                    new ArquivoLogBD(new Date() + "...Erro ao carregar dados" + ex);
                }
                ModelTable modelo = new ModelTable(dados, colunas);

                jTableAgenda.setModel(modelo);
                jTableAgenda.getColumnModel().getColumn(0).setPreferredWidth(50);
                jTableAgenda.getColumnModel().getColumn(0).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(1).setPreferredWidth(200);
                jTableAgenda.getColumnModel().getColumn(1).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(2).setPreferredWidth(100);
                jTableAgenda.getColumnModel().getColumn(2).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(3).setPreferredWidth(90);
                jTableAgenda.getColumnModel().getColumn(3).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(4).setPreferredWidth(75);
                jTableAgenda.getColumnModel().getColumn(4).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(5).setPreferredWidth(115);
                jTableAgenda.getColumnModel().getColumn(5).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(6).setPreferredWidth(169);
                jTableAgenda.getColumnModel().getColumn(6).setResizable(false);
                jTableAgenda.getColumnModel().getColumn(7).setPreferredWidth(90);
                jTableAgenda.getColumnModel().getColumn(7).setResizable(false);
                jTableAgenda.getTableHeader().setReorderingAllowed(false);
                jTableAgenda.setAutoResizeMode(jTableAgenda.AUTO_RESIZE_OFF);
                jTableAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                conex.desconecta();
                bar.setVisible(false);
                new ArquivoLogBD(new Date() + "...Finalizado com sucesso conexao com banco externo e salvando banco local");
            }
        }.start();
    }

    public void BaixarBD(String Sql) {
        new Thread() {
            public void run() {
                conex.conexao();
                conex.ExecutaSql(Sql);

                try {
                    conex.rs.first();

                    do {

                        List<String> list = new ArrayList();
                        list.add(Integer.toString(conex.rs.getInt("idkit_atleta")));
                        list.add(conex.rs.getString("nome"));
                        list.add(conex.rs.getString("cpf"));
                        list.add(conex.rs.getString("rg"));
                        list.add(conex.rs.getString("sexo"));
                        list.add(conex.rs.getString("telefone"));
                        list.add(conex.rs.getString("email"));
                        list.add(conex.rs.getString("data_nasc"));

                        dao.Adiciona(list);

                    } while (conex.rs.next());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "erro ao carregar" + ex);
                    new ArquivoLogBD(new Date() + "...Erro ao carregar dados" + ex);
                }
                conex.desconecta();
                bar.setVisible(false);
                new ArquivoLogBD(new Date() + "...Finalizado com sucesso conexao com banco externo e salvando banco local");
            }
        }.start();
    }

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExibeSalva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibeSalva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibeSalva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibeSalva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibeSalva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAgenda;
    private javax.swing.JButton jbtBaixar;
    private javax.swing.JButton jbtOrdenar;
    // End of variables declaration//GEN-END:variables
}
