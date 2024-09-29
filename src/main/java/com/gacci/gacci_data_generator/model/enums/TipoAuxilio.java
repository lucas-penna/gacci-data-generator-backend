package com.gacci.gacci_data_generator.model.enums;

public enum TipoAuxilio {
    CESTABASICA(1, "Cesta básica"),
    LEITE(2, "Leite"),
    SUPLEMENTO(3, "Suplemento"),
    MEDICAMENTO(4, "Medicamento"),
    OUTROS(5, "Outros");

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

    public static TipoAuxilio fromLabel(String label) {
        for (TipoAuxilio t : values()) {
            if (t.getLabel().equalsIgnoreCase(label)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Label inválido para TipoAuxilio: " + label);
    }
}
