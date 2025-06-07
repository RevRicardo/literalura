// Challenge ONE ALURA - GutendexAPI
// Desenvolvedor: Ricardo G.B. Lacerda
package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoBuscaDTO(
        @JsonAlias("results") List<LivroDTO> resultados
) {}