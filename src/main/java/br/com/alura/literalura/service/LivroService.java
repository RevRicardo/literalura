package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.AutorDTO;
import br.com.alura.literalura.dto.LivroDTO;
import br.com.alura.literalura.dto.ResultadoBuscaDTO;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final String ENDERECO_BASE = "https://gutendex.com/books/?search=";

    @Autowired
    private ConsumoAPI consumoApi;

    @Autowired
    private ConverteDados conversor;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public void buscarESalvarLivro(String titulo) {
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = ENDERECO_BASE + tituloCodificado;

        System.out.println("\nBuscando na API: " + url);

        String json = consumoApi.obterDados(url);

        ResultadoBuscaDTO resultado = conversor.obterDados(json, ResultadoBuscaDTO.class);

        if (resultado != null && resultado.resultados() != null && !resultado.resultados().isEmpty()) {
            LivroDTO livroDTO = resultado.resultados().get(0);

            Optional<Livro> livroExistente = livroRepository.findByTituloContainingIgnoreCase(livroDTO.titulo());

            if (livroExistente.isPresent()) {
                System.out.println("Livro '" + livroDTO.titulo() + "' já existe no banco de dados.");
                return;
            }

            Livro livro = new Livro(livroDTO);
            if (livroDTO.autores() != null && !livroDTO.autores().isEmpty()) {
                AutorDTO autorDTO = livroDTO.autores().get(0);
                Optional<Autor> autorExistente = autorRepository.findByNomeContainingIgnoreCase(autorDTO.nome());
                Autor autor;
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                    System.out.println("Autor '" + autor.getNome() + "' já existe no banco de dados. Reutilizando.");
                } else {
                    autor = new Autor(autorDTO);
                    autor = autorRepository.save(autor);
                    System.out.println("Novo autor salvo: " + autor.getNome());
                }
                livro.setAutor(autor);
            } else {
                System.out.println("Livro sem autor especificado na API.");
            }

            livroRepository.save(livro);
            System.out.println("\nLivro salvo com sucesso:");
            System.out.println(livro);
        } else {
            System.out.println("Nenhum livro encontrado para o título: '" + titulo + "' na Gutendex API.");
        }
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            System.out.println("\n--- LIVROS REGISTRADOS ---");
            livros.forEach(System.out::println);
            System.out.println("--------------------------");
        }
    }

    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            System.out.println("\n--- AUTORES REGISTRADOS ---");
            autores.forEach(System.out::println);
            System.out.println("---------------------------");
        }
    }

    public void listarAutoresVivosPorAno(int ano) {
        LocalDate anoInicio = LocalDate.of(ano, 1, 1);
        LocalDate anoFim = LocalDate.of(ano, 12, 31);

        List<Autor> todosAutores = autorRepository.findAll();

        List<Autor> autoresVivos = todosAutores.stream()
                .filter(a -> (a.getDataNascimento() == null || !a.getDataNascimento().isAfter(anoFim)) &&
                        (a.getDataFalecimento() == null || !a.getDataFalecimento().isBefore(anoInicio)))
                .collect(Collectors.toList());


        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano " + ano + ".");
        } else {
            System.out.println("\n--- AUTORES VIVOS NO ANO " + ano + " ---");
            autoresVivos.forEach(a -> System.out.println("Nome: " + a.getNome() +
                    ", Nascimento: " + (a.getDataNascimento() != null ? a.getDataNascimento().getYear() : "N/A") +
                    ", Falecimento: " + (a.getDataFalecimento() != null ? a.getDataFalecimento().getYear() : "N/A")));
            System.out.println("-------------------------------------");
        }
    }
}