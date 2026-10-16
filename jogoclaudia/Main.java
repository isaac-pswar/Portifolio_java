import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Personagem> personagens = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n===== SISTEMA DE PERSONAGENS RPG =====");
            
            System.out.println("1 - Cadastrar guerreiro");
            System.out.println("2 - Cadastrar mago");
            System.out.println("3 - Consultar personagens");
            System.out.println("4 - Realizar ataque");
            System.out.println("5 - Causar dano manual a um personagem");
            System.out.println("6 - Batalha interativa (você escolhe as ações)");
            System.out.println("7 - Batalha automática");
            System.out.println("8 - Cadastrar Rogue");
            System.out.println("9 - Sair");
            System.out.println("10 - Deletar Personagem");
            System.out.print("Escolha: ");

            switch (sc.nextLine().trim()) {
                
                case "1": cadastrarGuerreiro(sc); break;
                case "2": cadastrarMago(sc); break;
                case "3": consultar(); break;
                case "4": atacar(sc); break;
                case "5": causarDano(sc); break;
                case "6": batalha(sc, true); break;
                case "7": batalha(sc, false); break;
                case "8": cadastrarRogue(sc); break;
                case "9": rodando = false; break;
                case "10": DeletarPersonagem( sc); break;
                default: System.out.println("Opção inválida.");
            }
        }
        System.out.println("Até a próxima aventura!");
    }

    // ---------- Cadastros ----------
    

    private static void cadastrarGuerreiro(Scanner sc) {
        try {
            String nome = lerTexto(sc, "Nome: ");
            int nivel = lerInt(sc, "Nível: ");
            int vida = lerInt(sc, "Vida: ");
            String arma = lerTexto(sc, "Arma: ");
            personagens.add(new Guerreiro(nome, nivel, vida, arma));
            System.out.println("Guerreiro cadastrado!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cadastrarMago(Scanner sc) {
        try {
            String nome = lerTexto(sc, "Nome: ");
            int nivel = lerInt(sc, "Nível: ");
            int vida = lerInt(sc, "Vida: ");
            String magia = lerTexto(sc, "Tipo de magia: ");
            personagens.add(new Mago(nome, nivel, vida, magia));
            System.out.println("Mago cadastrado!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

private static void cadastrarRogue(Scanner sc) {
        try {
            String nome = lerTexto(sc, "Nome: ");
            int nivel = lerInt(sc, "Nível: ");
            int vida = lerInt(sc, "Vida: ");
            String Gadget = lerTexto(sc, "Tipo de Ferramenta: ");
            personagens.add(new Rogue(nome, nivel, vida, Gadget));
            System.out.println("Rogue cadastrado!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    private static void DeletarPersonagem(Scanner sc){
    Personagem vascado = escolher(sc, "Quem será vascado?");
    if (vascado == null) {
        System.out.println("Escolha inválida");
        return; 
    }
    
    personagens.remove(vascado);
    System.out.println("Personagem " + vascado.getNome() + " foi vascado com sucesso!");
    //deleta os personagens do arraylist usando o método escolher definido anteriormente.
}



    // ---------- Ações ----------
    private static void consultar() {
        if (personagens.isEmpty()) {
            System.out.println("Nenhum personagem cadastrado.");
            return;
        }
        for (Personagem p : personagens) p.exibirInformacoes();
    }

    private static void atacar(Scanner sc) {
        Personagem atacante = escolher(sc, "Quem vai atacar?");
        if (atacante == null) return;
        Personagem alvo = escolher(sc, "Quem será o alvo?");
        if (alvo == null) return;
        if (atacante == alvo) {
            System.out.println("Um personagem não pode atacar a si mesmo.");
            return;
        }
        atacante.atacar(alvo);
    }

    private static void causarDano(Scanner sc) {
        Personagem alvo = escolher(sc, "Quem vai receber dano?");
        if (alvo == null) return;
        int dano = lerInt(sc, "Quantidade de dano: ");
        if (dano <= 0) System.out.println("Dano inválido: a vida não foi alterada.");
        alvo.receberDano(dano);
    }

    private static void batalha(Scanner sc, boolean interativa) {
        Personagem p1 = escolher(sc, "Primeiro lutador:");
        if (p1 == null) return;
        Personagem p2 = escolher(sc, "Segundo lutador:");
        if (p2 == null) return;
        if (p1 == p2) {
            System.out.println("Escolha dois personagens diferentes.");
            return;
        }
        if (!p1.estaVivo() || !p2.estaVivo()) {
            System.out.println("Só personagens vivos podem batalhar.");
            return;
        }
        Batalha b = new Batalha(p1, p2);
        if (interativa) b.jogar(sc);
        else b.simular();
    }

    // ---------- Utilitários de entrada ----------
    private static Personagem escolher(Scanner sc, String titulo) {
        if (personagens.isEmpty()) {
            System.out.println("Nenhum personagem cadastrado.");
            return null;
        }
        System.out.println(titulo);
        for (int i = 0; i < personagens.size(); i++) {
            Personagem p = personagens.get(i);
            System.out.println((i + 1) + " - " + p.getNome() + " (vida " + p.getVida() + ")");
        }
        int n = lerInt(sc, "Número: ");
        if (n < 1 || n > personagens.size()) {
            System.out.println("Personagem inexistente.");
            return null;
        }
        return personagens.get(n - 1);
    }

    private static String lerTexto(Scanner sc, String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static int lerInt(Scanner sc, String msg) {
        System.out.print(msg);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // valor inválido; as regras de negócio rejeitam
        }
    }
}