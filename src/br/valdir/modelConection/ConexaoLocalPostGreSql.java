/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.valdir.modelConection;

import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author Valdir
 */
public class ConexaoLocalPostGreSql {

    public Statement stm;                                                       //Realiza pesquisa no Banco de dados
    public ResultSet rs;                                                        // Arazena a pesquisa do statement
    private final String driver = "org.postgresql.Driver";                      //  driver de conexao
    private final String caminho = "jdbc:postgresql://localhost:5432/javateste";// caminho do bd
    private final String usuario = "postgres";                                  // usuario do bd
    private final String senha = "root";                                        //senha do bd
    public Connection con;                                                      // responsavel pela conexao com banco

    public void conexao() {                                                     //Metodo responsavel por realizar a conexao com o BD
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao se conectar com banco de Dados:\n" + ex);
        }
    }

    public void executaSql(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao executar a consulta sql:\n" + ex);
        }

    }

    public void desconecta() {                                            //Metodo responsavel por realizar a desconexao com o BD
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se desconectaer do BD:\n" + ex);
        }
    }
}
