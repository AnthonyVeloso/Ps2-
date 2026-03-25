package mack.lp2.biblioteca.model;

public class emprestimo {
    private int livro_id;
    private String data_retirada;

    public emprestimo(int livro_id, String data_retirada) {
        this.livro_id = livro_id;
        this.data_retirada = data_retirada;
    }
    
    public int getLivro_id() { return livro_id; }
    public void setLivro_id(int livro_id) { this.livro_id = livro_id; }
    public String getData_retirada() { return data_retirada; }
    public void setData_retirada(String data_retirada) { this.data_retirada = data_retirada; }
}