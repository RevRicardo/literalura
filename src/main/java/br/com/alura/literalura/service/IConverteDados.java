// Challenge ONE ALURA - GutendexAPI
// Desenvolvedor: Ricardo G.B. Lacerda
package br.com.alura.literalura.service;

import org.springframework.stereotype.Component;

@Component
public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}