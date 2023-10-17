import java.util.EnumSet;

public class ListaLugaresPossiveis {
    public enum LugaresPossiveis {
        ENTRADA_PRINCIPAL, ENTRADA_DE_AUTOMOVEIS, DIDATICA_1, DIDATICA_2, DIDATICA_3,
        DIDATICA_4, DIDATICA_5, DIDATICA_6, DIDATICA_7, RESUN, BIBLIOTECA, AUDITORIO,
        REITORIA, CEET, CCBS, CENTRO_DE_VIVENCIA
    }

    private EnumSet<LugaresPossiveis> lugaresPossiveis;

    public ListaLugaresPossiveis() {
        lugaresPossiveis = EnumSet.allOf(LugaresPossiveis.class);
    }

    public String selecionarLugar(String lugar) {
        LugaresPossiveis lugarSelecionado = null;

        try {
            lugarSelecionado = LugaresPossiveis.valueOf(lugar.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Lugar inválido. Escolha um lugar da lista de lugares possíveis.";
        }

        if (lugaresPossiveis.contains(lugarSelecionado)) {
            lugaresPossiveis.remove(lugarSelecionado);
            return lugar + " foi selecionado.";
        } else {
            return lugar + " já foi selecionado ou não está disponível.";
        }
    }
}

