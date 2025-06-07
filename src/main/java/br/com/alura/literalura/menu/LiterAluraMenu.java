// Challenge ONE ALURA - GutendexAPI
// Desenvolvedor: Ricardo G.B. Lacerda
package br.com.alura.literalura.menu;

import br.com.alura.literalura.leitor.inserirDados;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LiterAluraMenu {

    private final Scanner leitura = inserirDados.leitura;

    @Autowired
    private LivroService livroService;

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("                 MENU LITERALURA                 ");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Buscar livro pelo título");
            System.out.println("2. Listar livros registrados");
            System.out.println("3. Listar todos os autores");
            System.out.println("4. Listar autor por nome");
            System.out.println("5. Listar autores por ano de nascimento");
            System.out.println("6. Listar autores por ano de falecimento");
            System.out.println("7. Listar autores vivos até o ano digitado");
            System.out.println("8. Listar livros por um trecho do título");
            System.out.println("9. Listar livros por um autor");
            System.out.println("10. Listar livros em um determinado idioma");
            System.out.println("11. TOP 10 de livros mais baixados");
            System.out.println("0. Sair");
            System.out.println("-------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.valueOf(leitura.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
            }
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.print("Digite o título do livro que deseja buscar: ");
                String tituloBusca = leitura.nextLine();
                livroService.buscarESalvarLivro(tituloBusca);
                break;
            case 2:
                livroService.listarLivrosRegistrados();
                break;
            case 3:
                livroService.listarAutores();
                break;
            case 4:
                System.out.print("Digite o nome do autor que deseja buscar: ");
                String nomeAutorBusca = leitura.nextLine();
                livroService.listarAutorPorNome(nomeAutorBusca);
                break;
            case 5:
                System.out.print("Digite o ano de nascimento para buscar autores: ");
                try {
                    int anoNascimento = Integer.parseInt(leitura.nextLine());
                    livroService.listarAutoresPorAnoDeNascimento(anoNascimento);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. Por favor, digite um número inteiro.");
                }
                break;
            case 6:
                System.out.print("Digite o ano de falecimento para buscar autores: ");
                try {
                    int anoFalecimento = Integer.parseInt(leitura.nextLine());
                    livroService.listarAutoresPorAnoDeFalecimento(anoFalecimento);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. Por favor, digite um número inteiro.");
                }
                break;
            case 7:
                System.out.print("Digite o ano limite para autores vivos: ");
                try {
                    int ano = Integer.parseInt(leitura.nextLine());
                    livroService.listarAutoresVivosPorAno(ano);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. Por favor, digite um número inteiro.");
                }
                break;
            case 8:
                System.out.println("Opção 8: Listar livros por um trecho do título (Ainda não implementado no serviço)");
                break;
            case 9:
                System.out.println("Opção 9: Listar livros por um autor (Ainda não implementado no serviço)");
                break;
            case 10:
                System.out.println("Opção 10: Listar livros em um determinado idioma (Ainda não implementado no serviço)");
                break;
            case 11:
                System.out.println("Opção 11: TOP 10 de livros mais baixados (Ainda não implementado no serviço)");
                break;
            case 0:
                System.out.println("Saindo da aplicação. Até mais!");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
        inserirDados.pausar();
    }
}
