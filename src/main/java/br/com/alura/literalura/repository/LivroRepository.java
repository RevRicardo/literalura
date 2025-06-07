// Challenge ONE ALURA - GutendexAPI
// Desenvolvedor: Ricardo G.B. Lacerda
package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findTop10ByOrderByDownloadsRealizadosDesc();
    List<Livro> findByIdioma(String idioma);
    List<Livro> findByTituloContaining(String titulo);
    List<Livro> findByAutorNomeContainingIgnoreCase(String nomeAutor);
}