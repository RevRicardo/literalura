package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nome);
    List<Autor> findByDataNascimentoBeforeOrDataNascimentoIsNullAndDataFalecimentoAfterOrDataFalecimentoIsNull(LocalDate anoLimite, LocalDate anoLimite2);
    List<Autor> findByDataNascimentoBetween(LocalDate startOfYear, LocalDate endOfYear);
    List<Autor> findByDataFalecimentoBetween(LocalDate startOfYear, LocalDate endOfYear);

}
