public class Personagem {
    private String nome;
    private int nivel;
    private int vida;

    public Personagem(String nome, int nivel, int vida) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("O personagem deve possuir um nome.");   // RN01
        if (nivel <= 0)
            throw new IllegalArgumentException("O nível deve ser maior que zero.");     // RN02
        if (vida <= 0)
            throw new IllegalArgumentException("A vida deve ser maior que zero.");      // RN03
        this.nome = nome;
        this.nivel = nivel;
        this.vida = vida;
    }

    public String getNome() { return nome; }
    public int getNivel()   { return nivel; }
    public int getVida()    { return vida; }

    // RN10 / RN11
    public boolean estaVivo() { return vida > 0; }

    // RN08, RN09, RN13
    public void receberDano(int dano) {
        if (dano <= 0) return;
        vida = Math.max(0, vida - dano);
        System.out.println(nome + " recebeu " + dano + " de dano. Vida atual: " + vida);
        if (!estaVivo()) System.out.println(nome + " foi derrotado!");
    }

    // RN12: usado pelas subclasses antes de atacar
    protected boolean podeAtacar() {
        if (!estaVivo()) {
            System.out.println(nome + " está derrotado e não pode atacar.");
            return false;
        }
        return true;
    }

    // Dano base; cada tipo pode sobrescrever
    protected int calcularDano() { return nivel * 3; }

    // Ataque genérico (RF05) - sobrescrito nos tipos específicos (RN07)
    public void atacar(Personagem alvo) {
        if (!podeAtacar()) return;
        System.out.println(nome + " ataca " + alvo.getNome() + ".");
        alvo.receberDano(calcularDano());
    }

    // RF04
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome + " | Nível: " + nivel + " | Vida: " + vida
                + " | Situação: " + (estaVivo() ? "Vivo" : "Derrotado"));
    }





}
