package mack.lp2.biblioteca.model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    
    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return titulo; }
    public void setNome(String nome) { this.titulo = nome; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}