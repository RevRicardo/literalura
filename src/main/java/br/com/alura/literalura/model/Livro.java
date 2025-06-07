// Challenge ONE ALURA - GutendexAPI
// Desenvolvedor: Ricardo G.B. Lacerda
package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.LivroDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long idLivro;

    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @Column(name = "idioma")
    private String idioma;

    @Column(name = "downloads_realizados")
    private Integer downloadsRealizados;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    public Livro() {}

    public Livro(LivroDTO livroDTO) {
        this.titulo = livroDTO.titulo();
        this.idioma = livroDTO.idiomas() != null && !livroDTO.idiomas().isEmpty() ? livroDTO.idiomas().get(0) : "und"; // 'und' para indefinido
        this.downloadsRealizados = livroDTO.downloadsRealizados();
    }

    // Getters e Setters
    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloadsRealizados() {
        return downloadsRealizados;
    }

    public void setDownloadsRealizados(Integer downloadsRealizados) {
        this.downloadsRealizados = downloadsRealizados;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "---- LIVRO ----\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ? autor.getNome() : "Desconhecido") + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de Downloads: " + downloadsRealizados + "\n" +
                "---------------\n";
    }
}