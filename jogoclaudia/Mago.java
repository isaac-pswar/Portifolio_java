public class Mago extends Personagem {
    public static Object nome;
    private String tipoMagia;

    public Mago(String nome, int nivel, int vida, String tipoMagia) {
        super(nome, nivel, vida);
        if (tipoMagia == null || tipoMagia.isBlank())
            throw new IllegalArgumentException("O mago deve possuir um tipo de magia."); // RN06
        this.tipoMagia = tipoMagia;
    }

    public String getTipoMagia() { return tipoMagia; }

    @Override
    protected int calcularDano() { return getNivel() * 6; }

    @Override
    public void atacar(Personagem alvo) {
        if (!podeAtacar()) return;
        System.out.println("O mago " + getNome() + " lança " + tipoMagia
                + " em " + alvo.getNome() + "!");
        alvo.receberDano(calcularDano());
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("   Tipo: Mago | Magia: " + tipoMagia);
    }
}
