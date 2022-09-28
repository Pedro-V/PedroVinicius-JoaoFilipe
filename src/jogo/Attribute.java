package jogo;

public enum Attribute {
    MONSTER("Monstro"), CHEFE("Chefe"), CURA("Cura"), BOLAFOGO("Bola de Fogo"),
    ARMADILHA("Armadilha"), VAZIA("Vazia");
    private String descricao;
    private String valor;
    // Uma mensagem a ser mostrada caso o atributo ja tenha sido utilizado
    private String mensagemAtributoUtilizado;
    // Um valor associado a ação do atributo
    private int valor_associado;

    Attribute(String valor) {
        this.valor = valor;
        setDescricaoMensagem();
        inicializaValorAssociado();
    }

    private void setDescricaoMensagem(){
        switch (valor) {
            case "Monstro":
                descricao = "Você encontrou um monstro nessa sala! Ele tem 3 de vida e 1 de dano.\nSó após derrotar ele você pode sair dessa sala.";
                mensagemAtributoUtilizado = "O monstro dessa sala já está morto.";
                break;
            case "Chefe":
                descricao = "Você encontrou o chefe! Para obter a safira, você precisa derrotá-lo!\nO chefe tem 8 de vida e 2 de dano! Tome cuidado.";
                mensagemAtributoUtilizado = "O chefe foi derrotado, parabéns!";
                break;
            case "Cura":
                descricao = "Essa sala possui uma aura curadora!\nVocê automaticamente recebeu uma pequena quantia de cura!";
                mensagemAtributoUtilizado = "";
                break;
            case "Bola de Fogo":
                descricao = "Essa sala possui um monstro de fogo! Derrote-o e ganhe um feitiço de bola de fogo!";
                mensagemAtributoUtilizado = "O monstro de fogo foi derrotado.";
                break;
            case "Armadilha":
                descricao = "Ao entrar nessa sala você ativou uma armadilha automática! Voce sofre uma pequena quantia de dano!";
                mensagemAtributoUtilizado = "";
                break;
            case "Vazia":
                descricao = "";
                mensagemAtributoUtilizado = "";
                break;
        }
    }

    private void inicializaValorAssociado() {
        switch (valor) {
            case "Armadilha":
                valor_associado = 1; // precisa ser positivo para decremento não positivar o valor
                break;
            case "Cura":
                valor_associado = 2;
                break;
            case "Bola de Fogo":
                valor_associado = 3; // valor associado com a bola de fogo
                break;
            default:
                valor_associado = 0;
                break;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor_associado() {
        return valor_associado;
    }

    public String getmensagemAtributoUtilizado() {
        return mensagemAtributoUtilizado + '\n';
    }
}
