package br.valdir.modelConection;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexaoMySqlExterna {

    public Statement stm;
    public ResultSet rs;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String caminho = "jdbc:mysql://wsistem_corrid.mysql.dbaas.com.br/wsistem_corrid";
    private final String usuario = "wsistem_corrid";
    private final String senha = "wsistem2017";

    public Connection con;

    //Metodo responsavel por realizar a conexao com o BD
    public void conexao() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, usuario, senha);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao se conectar com banco de Dados:\n" + ex);
        }
    }

    //Metodo responsavel por realizar uma query no BD
    public void ExecutaSql(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao executar query:\n" + ex);
        }

    }

    //Metodo responsavel por encerrar a conexao com o BD           
    public void desconecta() {
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao se desconectar\n" + ex);
        }
    }
}
