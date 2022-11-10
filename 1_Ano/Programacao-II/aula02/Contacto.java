public class Contacto {
   private String nome;
   private String telefone;
   private String eMail;
 
   public Contacto(String _nome, String _telefone) {
     this.nome = _nome;
     this.telefone = _telefone;
   }
 
   public Contacto(String _nome, String _telefone, String _eMail) {
     this.nome = _nome;
     this.telefone = _telefone;
     this.eMail = _eMail;
   }
 
   public String nome(){ return nome; }
   public String telefone(){ return telefone; }
   public String eMail(){ return eMail; }
 
 }