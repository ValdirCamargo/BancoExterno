package br.valdir.modelView;

import br.valdir.modelConection.ConexaoLocalMySql;
import br.valdir.modelConection.ConexaoMySqlExterna;
import br.valdir.modelDao.DaoModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ApenasTeste extends javax.swing.JFrame {

    ConexaoMySqlExterna conex = new ConexaoMySqlExterna();
    ConexaoLocalMySql conexl = new ConexaoLocalMySql();
    DaoModel dao = new DaoModel();

    public ApenasTeste() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jButton1)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jButton1)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UpSegundo("SELECT * FROM kit_atleta");
    }//GEN-LAST:event_jButton1ActionPerformed

    public void UpSegundo(String Sql) {
        int j = 0;
        int i = 0;
        conex.conexao();
        conexl.conexaoL();
        conex.ExecutaSql(Sql);

        try {
            conex.rs.first();

            do {
                String id = Integer.toString(conex.rs.getInt("idkit_atleta"));
                conexl.ExecutaSqlLi("SELECT * FROM tabela_teste where idtabela_teste ='" + id + "'");
                conexl.rs.first();
                String id2 = Integer.toString(conexl.rs.getInt("idtabela_teste"));
                String nome = (conex.rs.getString("nome"));
                String nome2 = (conexl.rs.getString("nome"));
                String sexo = (conex.rs.getString("sexo"));
                String sexo2 = (conexl.rs.getString("sexo"));
                String nasc = (conex.rs.getString("data_nasc"));
                String nasc2 = (conexl.rs.getString("nascimento"));

                List<String> list = new ArrayList();
                list.add(Integer.toString(conex.rs.getInt("idkit_atleta")));
                list.add(conex.rs.getString("nome"));
                list.add(conex.rs.getString("cpf"));
                list.add(conex.rs.getString("rg"));
                list.add(conex.rs.getString("sexo"));
                list.add(conex.rs.getString("telefone"));
                list.add(conex.rs.getString("email"));
                list.add(conex.rs.getString("data_nasc"));
                if (id.equals(id2)) {
                    if (nome.equals(nome2)) {
                        if (sexo.equals(sexo2)) {
                            if (nasc.equals(nasc2)) {
                                i++;
                            } else {
                                j++;
                                dao.Update(list);
                            }
                        } else {
                            j++;
                            dao.Update(list);
                        }
                    } else {
                        j++;
                        dao.Update(list);
                    }
                } else {
                    j++;
                    dao.Update(list);
                }
            } while (conex.rs.next());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao carregar dados em segundo plano contact o adminestrador : " + ex);
        }
        System.out.println("Dados analisados: " + i);
        System.out.println("Dados alterados: " + j);
        conex.desconecta();
        conexl.desconectaL();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApenasTeste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
