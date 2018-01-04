
package br.valdir.modelConection;

import br.valdir.log.ArquivoLog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class ConexaoLocalMySql {
    public Statement stm;
    public ResultSet rs;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String caminho = "jdbc:mysql://localhost/testejava";
    private final String usuario = "root";
    private final String senha = "root";

    public Connection con;

    //Metodo responsavel por realizar a conexao com o BD
    public void conexaoL() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho, usuario, senha);
           //JOptionPane.showMessageDialog(null, "Conectou");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao se conectar com banco de Dados:\n" + ex);
            new ArquivoLog("...Erros 1"+ex);
        }
    }

    //Metodo responsavel por realizar uma query no BD
    public void ExecutaSqlLi(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao executar query:\n" + ex);
        }
    }
    
    //Metodo responsavel por encerrar a conexao com o BD           
    public void desconectaL() {
        try {
            con.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao se desconectar\n" + ex);
        }
    }
}
