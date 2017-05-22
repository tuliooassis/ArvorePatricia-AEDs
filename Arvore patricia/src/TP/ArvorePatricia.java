package TP;

import java.util.Vector;

public class ArvorePatricia {
    private static abstract class PatNo { }
    private static class PatNoInt extends PatNo {
      int index; PatNo esq, dir;
    }  
    private static class PatNoExt extends PatNo {
      String chave; // @{\it O tipo da chave depende da aplica\c{c}\~ao}@
      private Vector<Integer> linhas;
      private Vector<Integer> colunas;
    }

    private PatNo raiz;
    private int nbitsChave;

    public void add (PatNoExt t, int linha, int coluna){
        t.linhas.add(linha);
        t.colunas.add(coluna);
    }

    public int bit (int a,String k){
        int []binario = new int[130];
        for (int i = 0; i < k.length() && i<  16; i++){
            for (int j = 7, c =k.charAt(i) ; j >=0; j--){
                binario[i*8+j] = c%2 ;
                c/=2;
            }
        }
        return binario[a];
    }

    public void bit (String k){
        int []binario = new int[130];
        for (int i = 0; i < k.length(); i++){
            for (int j = 7, c =k.charAt(i) ; j >=0; j--){
                binario[i*8+j] = c%2 ;
                c/=2;
            }
        }
        for (int i = 0; i < 128; i ++){
            if (i % 8 == 0)
                System.out.print(" ");
            System.out.print(binario[i]);
        }
    }

    // @{\it Verifica se p \'e n\'o externo}@
    private boolean eExterno (PatNo p) {    
        Class classe = p.getClass ();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    private PatNo criaNoInt (int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt ();
        p.index = i; p.esq = esq; p.dir = dir;
        return p;
    }

    private PatNo criaNoExt (String k,int linha, int coluna) {
        PatNoExt p = new PatNoExt ();
        p.colunas=new Vector<Integer>();
        p.colunas.add(coluna);
        p.linhas=new Vector<Integer>();
        p.linhas.add(linha);
        p.chave = k;
        return p;
    }

    private void pesquisa (String k, PatNo t) {
        if (this.eExterno (t)) {
            PatNoExt aux = (PatNoExt)t;
            if (aux.chave.equalsIgnoreCase(k)){
                System.out.println ("Palavra encontrada: " + k + "\nlinha\tcoluna");
                for (int i = 0; i < aux.linhas.size(); i++){
                    System.out.println(aux.linhas.get(i) + "\t"+ aux.colunas.get(i));
                }
            }
            else System.out.println ("Palavra nao encontrada \n");
        }
        else { 
            PatNoInt aux = (PatNoInt)t;
            if (this.bit (aux.index, k) == 0) pesquisa (k, aux.esq);
            else pesquisa (k, aux.dir);
        }
    }

    private PatNo insereEntre (String k, PatNo t, int i, int linha, int coluna) {
        PatNoInt aux = null; 
        if (!this.eExterno (t)) aux = (PatNoInt)t;
        if (this.eExterno (t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
            PatNo p = this.criaNoExt (k,linha,coluna);
            if (this.bit (i, k) == 1) return this.criaNoInt (i, t, p);
            else return this.criaNoInt (i, p, t);
        }
        else {
            if (this.bit (aux.index, k) == 1) 
                aux.dir = this.insereEntre (k, aux.dir, i, linha,  coluna);
            else aux.esq = this.insereEntre (k, aux.esq, i, linha,  coluna);
            return aux;
        }
    }

    private PatNo insere (String k, int linha, int coluna, PatNo t) {
        if (t == null) return this.criaNoExt (k,linha,coluna);
        else {
            PatNo p = t;
            while (!this.eExterno (p)) {
                PatNoInt aux = (PatNoInt)p;
                if (this.bit (aux.index, k) == 1) p = aux.dir; else p = aux.esq;
            }
            PatNoExt aux = (PatNoExt)p;
            int i = 1; // @{\it acha o primeiro bit diferente}@
            while ((i <= this.nbitsChave)&&(this.bit (i, k) == this.bit (i, aux.chave)))
                i++;
            if (i > this.nbitsChave) {
                this.add(aux, linha, coluna);
                return t;
            }
            else return this.insereEntre (k, t, i,linha,coluna);
        }
    }

    private void central (PatNo pai, PatNo filho, String msg) {
        if (filho != null) {
            if (!this.eExterno (filho)) {
                PatNoInt aux = (PatNoInt)filho; 
                central (filho, aux.esq, "ESQ");
                if (pai != null)
                    System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Int: " + aux.index);
                else System.out.println ("Pai: "+ pai + " " + msg+ " Int: " + aux.index);
                central (filho, aux.dir, "DIR");
            } else {
                PatNoExt aux = (PatNoExt)filho;
                if (pai != null)
                     System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Ext: " + aux.chave);
                else System.out.println ("Pai: "+ pai + " " + msg+ " Ext: " + aux.chave);
            }
        }
    }

    public void imprime () {
        this.central (null, this.raiz, "RAIZ");
    }

    public ArvorePatricia (int nbitsChave) {
        this.raiz = null; this.nbitsChave = nbitsChave; 
    }

    public void pesquisa (String k) { this.pesquisa (k, this.raiz); }

    public void insere (String k, int linha, int coluna) {
            this.raiz = this.insere (k, linha, coluna, this.raiz); 
    } 
}
