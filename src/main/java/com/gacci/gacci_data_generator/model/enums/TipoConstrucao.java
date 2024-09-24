package com.gacci.gacci_data_generator.model.enums;

public enum TipoConstrucao {
    ALVENARIA(1, "Alvenaria"),
    MADEIRA(2, "Madeira"),
    MISTA(3, "Mista"),
    OUTRO(4, "Outro");

    private final int id;
    private final String label;

    TipoConstrucao(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoConstrucao fromId(int id) {
        for (TipoConstrucao t : values()) {
            if (t.id == id) {
                return t;
            }
        }
        throw new IllegalArgumentException("Id inválido para TipoConstrucao: " + id);
    }
}
