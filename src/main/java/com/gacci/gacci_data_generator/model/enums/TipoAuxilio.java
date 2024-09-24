package com.gacci.gacci_data_generator.model.enums;

public enum TipoAuxilio {
    ALIMENTACAO(1, "Alimentação"),
    SAUDE(2, "Saúde"),
    EDUCACAO(3, "Educação"),
    MORADIA(4, "Moradia"),
    OUTRO(5, "Outro");

    private final int id;
    private final String label;

    TipoAuxilio(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoAuxilio fromId(int id) {
        for (TipoAuxilio t : values()) {
            if (t.id == id) {
                return t;
            }
        }
        throw new IllegalArgumentException("Id inválido para TipoAuxilio: " + id);
    }
}
