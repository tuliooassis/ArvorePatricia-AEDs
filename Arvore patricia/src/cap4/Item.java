package cap4;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class Item{
  private int chave;
  private Vector<Integer> linhas;
  private Vector<Integer> colunas;
  private static int linha=0, coluna=0;
  // @{\it outros componentes do registro}@

  public Item (int chave) {
      this.chave = chave;
      linhas = new Vector <Integer>();
      colunas = new Vector <Integer>();
  }
   public Item () {
      this.chave=0;
      linhas = new Vector <Integer>();
      colunas = new Vector <Integer>();
  }
  
  public int compara (Item it) {
    Item item = (Item) it;
    if (this.chave < item.chave) return -1;
    else if (this.chave > item.chave) return 1;
    if(it.linhas != null){
        for(int i=0;i<it.linhas.size();i++){
            this.linhas.add(it.linhas.get(i));
            this.colunas.add(it.colunas.get(i));
        }
    }
    return 0;
  }
  
  public void alteraChave (Object chave) {
    Integer ch = (Integer) chave; this.chave = ch.intValue ();
  }
  
  public Object recuperaChave () { return new Integer (this.chave); }
  
  public String toString () { return "" + this.chave; }
  
  public void gravaArq (RandomAccessFile arq) throws IOException {
    arq.writeInt (this.chave);
  }
  
  public void leArq (RandomAccessFile arq) throws IOException {
      char c;
        
      String s = "";
      
    try {
        for(int i=0;i < 16 ;i++){
            System.out.println("oi");
            c = arq.readChar();
            System.out.println("C: " + c);
            if(c==' '||c=='\t'){
                this.coluna ++;
                break;
            }else if(c=='\n'){
                this.coluna = 0;
                this.linha++;
                break;
            }else{
                s += c;
            }
        }
        this.chave=Integer.parseInt(s);
        System.out.println("S:"+s);
        this.linhas.add(this.linha);
        this.colunas.add(this.coluna);
    }
    catch (EOFException a){
        System.out.println("deu ruim em item");
        return;
    }
  }
 
  public static int tamanho () { return 4; /* @{\it 4 bytes}@ */ }
}