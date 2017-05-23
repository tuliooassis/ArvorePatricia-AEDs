package TP;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TP {
    public static void main (String[] args) throws FileNotFoundException, IOException, Exception {
        ArvorePatricia dicionario = new ArvorePatricia (8*16);

        try {
            ExtraiPalavra ep = new ExtraiPalavra("del.txt", "arq.txt");
            String palavra = ep.proximaPalavra();
            ep.setColunaAtual(1);
            while (palavra != null){
                dicionario.insere(palavra, ep.getLinhaAtual(),ep.getColunaAtual());
                ep.setColunaAtual(ep.getColunaAtual()+palavra.length()+1);
                palavra = ep.proximaPalavra();
            }
            dicionario.pesquisa("formacao");
        }
        catch (FileNotFoundException a){
            System.out.println("Arquivo não encontrado!");
        }
    }
}
