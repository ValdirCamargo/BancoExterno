/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.valdir.modelDao;

import br.valdir.modelConection.ConexaoLocalMySql;
import br.valdir.modelConection.ConexaoLocalPostGreSql;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoModel {
    ConexaoLocalMySql conexL= new ConexaoLocalMySql();
    ConexaoLocalPostGreSql conexP = new ConexaoLocalPostGreSql();
    int i=1;
    public void Adiciona(List<String> banco){
        conexL.conexaoL();
        try {
            PreparedStatement pst = conexL.con.prepareStatement("INSERT INTO tabela_teste(idtabela_teste, nome, cpf, rg, sexo, telefone, email, nascimento) VALUES (?,?,?,?,?,?,?,?)");
            pst.setInt(1,Integer.parseInt(banco.get(0)));
            pst.setString(2,banco.get(1));
            pst.setString(3,banco.get(2));
            pst.setString(4,banco.get(3));
            pst.setString(5,banco.get(4));
            pst.setString(6,banco.get(5));
            pst.setString(7,banco.get(6));
            pst.setString(8,banco.get(7));
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DaoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conexL.desconectaL();
    }
    
    public void Update(List<String> banco){
        
        conexL.conexaoL();
        try {
            PreparedStatement pst = conexL.con.prepareStatement("UPDATE tabela_teste SET idtabela_teste=?, nome=?, cpf=?, rg=?,sexo=?, telefone=?, email=?, nascimento=? WHERE idtabela_teste=?");
            pst.setInt(1,Integer.parseInt(banco.get(0)));
            pst.setString(2,banco.get(1));
            pst.setString(3,banco.get(2));
            pst.setString(4,banco.get(3));
            pst.setString(5,banco.get(4));
            pst.setString(6,banco.get(5));
            pst.setString(7,banco.get(6));
            pst.setString(8,banco.get(7));
            pst.setInt(9,Integer.parseInt(banco.get(0)));
            pst.execute();
            System.out.println("Total de Dados alterados: "+i++ +" id: "+banco.get(0));
        } catch (SQLException ex) {
            System.out.println("Problemas ao executar update : "+ex);    
        }
        
        conexL.desconectaL();
    }
    
}
