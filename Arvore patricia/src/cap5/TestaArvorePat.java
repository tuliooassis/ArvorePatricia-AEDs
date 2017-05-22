package cap5;
import cap4.*;
import cap5.*;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestaArvorePat {
  public static void main (String[] args) throws FileNotFoundException, IOException {
    ArvorePatricia dicionario = new ArvorePatricia (8);
    int min = 32, max = 126;
    
    char vetor[] = new char[max-min+1];
//
//    for (int i = min; i <= max; i++)
//      vetor[i-min] = (char)i;
//
//    // @{\it Gera uma permuta\c{c}\~ao aleat\'oria de chaves dos caracteres UNICODE 32 a  126}@
//    PermutacaoRandomica.permut (vetor, vetor.length);
//    
//    // @{\it Insere cada chave na \'arvore}@
//    for (int i = 0; i < vetor.length; i++) { 
//      char c = vetor[i];
//      dicionario.insere (c);
//      System.out.println ("Inseriu chave"+ i + ": " + (int)c + " -- char:" + c);
//    }
//    dicionario.imprime ();
//
//    // @{\it Gera outra permuta\c{c}\~ao aleat\'oria de chaves}@
//    PermutacaoRandomica.permut (vetor, vetor.length);
//
//    // @{\it Pesquisa cada chave na \'arvore}@
//    for (int i = 0; i < vetor.length; i++) {
//      char c = vetor[i];
//      System.out.println ("Pesquisando chave" + i + ": " + c);
//      dicionario.pesquisa (c);
//    }


    //char[] c = new char[3];
    //c[0]='A';c[1]='B';c[2]='C';
//    String c = "ABCDEFGHIJ";
//    for (int i = 0; i < 128; i ++){
//        int bit = dicionario.bit(i, c);
//
//        if (i % 8 == 0)
//              System.out.print(" ");
//          System.out.print(bit);
//          
//    }
    try{
    Item it = new Item();
    RandomAccessFile arq = new RandomAccessFile("./arq.txt", "r");
    it.leArq(arq);
    
    for (int i = 0; i < 128; i ++){
        int bit = dicionario.bit(i, it.toString());

        if (i % 8 == 0)
              System.out.print(" ");
          System.out.print(bit);
          
    }
    } catch (FileNotFoundException a){
        System.out.println("deu ruim");
    } catch (EOFException a ){
        System.out.println("deu ruim tb");
    }
  }
}
