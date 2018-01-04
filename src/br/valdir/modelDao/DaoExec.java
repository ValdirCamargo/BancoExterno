package br.valdir.modelDao;

import br.valdir.log.ArquivoLogBD;
import br.valdir.modelConection.ConexaoLocalMySql;
import br.valdir.modelConection.ConexaoMySqlExterna;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoExec extends Thread implements Runnable {

    ConexaoMySqlExterna conex = new ConexaoMySqlExterna();
    ConexaoLocalMySql conexl = new ConexaoLocalMySql();
    DaoModel dao = new DaoModel();
    private final int tempo;

    public DaoExec(int tempo) {
        super();
        this.tempo = tempo;
    }

    @Override
    public void run() {
        try {
            
            int i = 0;
            while (true) {
                DaoExec.sleep(tempo);
                System.out.println("oi executei em segundo Plano :" + i++ + "X");
                UpSegundo("SELECT * FROM kit_atleta utf8 order by nome ");
                ArquivoLogBD arquivoLogBD = new ArquivoLogBD(new Date()+"...Iniciando execucao da Thread ")
                ;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DaoExec.class.getName()).log(Level.SEVERE, null, ex);
            ArquivoLogBD arquivoLogBD = new ArquivoLogBD(new Date()+"...Erro ao executar a Thread:  "+ex);
        }
    }

    public void UpSegundo(String Sql) {

        int j = 0;
        int count = 0;
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
                                count++;
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
            ArquivoLogBD arquivoLogBD = new ArquivoLogBD(new Date()+"...erro ao carregar dados em segundo plano "+ex);
        }
        System.out.println("Dados analisados: " + count);
        System.out.println("Dados alterados: " + j);
        conex.desconecta();
        conexl.desconectaL();
        ArquivoLogBD arquivoLogBD = new ArquivoLogBD(new Date()+"...Finalizada execucao da Threaad... Dados analizados: "+count +"\n Dados Alterados: "+j);
    }
}
