// Challenge ONE ALURA - GutendexAPI
// Desenvolvedor: Ricardo G.B. Lacerda
package br.com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Component;

@Component
public class ConsumoAPI {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.err.println("Erro no consumo da API: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar dados da API: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Busca interrompida na API: " + e.getMessage());
            throw new RuntimeException("Requisição interrompida: " + e.getMessage(), e);
        }

        String json = response.body();
        return json;
    }
}
