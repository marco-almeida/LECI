package Ex03.pt.ua.prog2;

public class Contacto2 {

    private String nome;
    private String telefone;
    private String eMail;
  
    public Contacto2(String _nome, String _telefone) {
      this.nome = _nome;
      this.telefone = _telefone;
    }
  
    public Contacto2(String _nome, String _telefone, String _eMail) {
      this.nome = _nome;
      this.telefone = _telefone;
      this.eMail = _eMail;
    }
  
    public String nome(){ return nome.toUpperCase(); }
    public String telefone(){ return telefone; }
    public String eMail(){ return eMail; }
  }