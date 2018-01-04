
package br.valdir.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;


public class ArquivoLogBD 
{
   
    File arquivo;
    FileReader fileReader;
    BufferedReader bufferedReader;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    
    public ArquivoLogBD(String erros)
    {
        escreveLog(erros);
    }
    
    //Metodo que recebe os  erros e escreve dentro do arquivo
    private void escreveLog(String erros)
    {
      
        try 
        {
            arquivo = new File("LogsBD.log");
            fileReader = new FileReader(arquivo);
            bufferedReader = new BufferedReader(fileReader);
            Vector texto = new Vector();
            while(bufferedReader.ready())
            {
                texto.add(bufferedReader.readLine());
            }
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < texto.size(); i++) 
            {
                bufferedWriter.write(texto.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.write(erros);
            bufferedReader.close();
            bufferedWriter.close();
        } 
        catch (FileNotFoundException ex) 
        {
            try 
            {
                arquivo.createNewFile();
                escreveLog(erros);
            }
            catch (IOException ex1) 
            {
                System.exit(0);
            }
        }
        catch (IOException er)
        {
            System.exit(0);
        }    
        
    }
}
