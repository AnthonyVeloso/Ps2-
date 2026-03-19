package mack.lp2.biblioteca.model;
public class Livro {
    private int id;
    private String titulo;
    String autor;
    
    
    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.nome = titulo;
        this.autor = autor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
