package com.gacci.gacci_data_generator.model.enums;

public enum Escolaridade {
    FUNDAMENTAL(1, "Fundamental"),
    MEDIO(2, "Médio"),
    SUPERIOR(3, "Superior"),
    POS_GRADUACAO(4, "Pós-Graduação"),
    MESTRADO(5, "Mestrado"),
    DOUTORADO(6, "Doutorado");

    private final int id;
    private final String label;

    Escolaridade(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Escolaridade fromId(int id) {
        for (Escolaridade e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Id inválido para Escolaridade: " + id);
    }
}

