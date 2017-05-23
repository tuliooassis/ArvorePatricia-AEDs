package TP;
import java.util.StringTokenizer;
import java.io.*;
public class ExtraiPalavra {
    private BufferedReader arqDelim, arqTxt;
    private StringTokenizer palavras;
    private String delimitadores;
    private int linhaAtual, colunaAtual;

    public ExtraiPalavra (String nomeArqDelim, String nomeArqTxt) 
    throws Exception {
        this.arqDelim = new BufferedReader (new FileReader (nomeArqDelim));
        this.arqTxt = new BufferedReader (new FileReader (nomeArqTxt));
        // @{\it Os delimitadores devem estar juntos em uma \'unica linha do arquivo}@ 
        this.delimitadores = arqDelim.readLine() + "\r\n \t"+'\n'; 
        this.linhaAtual = 0;
        this.colunaAtual = 1;
        this.palavras = null;
    }
        
    public String proximaPalavra () throws Exception{
        if (palavras == null || !palavras.hasMoreTokens()) {
            String linha = arqTxt.readLine();
            this.linhaAtual++;
            this.colunaAtual = 1;
            if (linha == null) return null; 
            this.palavras = new StringTokenizer (linha, this.delimitadores);
            if (!palavras.hasMoreTokens()) return ""; // @{\it ignora delimitadores}@
        }
//        this.colunaAtual++;
        return this.palavras.nextToken ();
    }  
    public void fecharArquivos () throws Exception {
        this.arqDelim.close(); 
        this.arqTxt.close();
    }

    public int getLinhaAtual() {
        return linhaAtual;
    }

    public int getColunaAtual() {
        return colunaAtual;
    }

    public void setColunaAtual(int colunaAtual) {
        this.colunaAtual = colunaAtual;
    }    
}