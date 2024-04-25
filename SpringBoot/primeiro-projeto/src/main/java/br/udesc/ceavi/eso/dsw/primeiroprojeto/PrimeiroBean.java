package br.udesc.ceavi.eso.dsw.primeiroprojeto;

public class PrimeiroBean {
    private String mensagem;

    public PrimeiroBean(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return this.mensagem;
    }

    public void  setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    @Override
    public String toString(){
        return "PrimeiroBean [mensagem=" + mensagem + "]";
    }
}
