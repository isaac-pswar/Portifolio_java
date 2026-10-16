public class Rogue extends Personagem {
    private String Gadget;

    public Rogue(String nome, int nivel, int vida, String Gadget) {
        super(nome, nivel, vida);
        if (Gadget == null || Gadget.isBlank())
            throw new IllegalArgumentException("O rogue precisa de sua ferramenta."); // RN06
        this.Gadget = Gadget;
    }

    public String getGadget() { return Gadget; }

    @Override
    protected int calcularDano() { return getNivel() * 500; }

    @Override
    public void atacar(Personagem alvo) {
        if (!podeAtacar()) return;
        System.out.println("Rogue usa " + getNome() + " lança " + Gadget
                + " em " + alvo.getNome() + "!");
        alvo.receberDano(calcularDano());
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   Tipo: Rogue | Ferramenta: " + Gadget);
    }
}
