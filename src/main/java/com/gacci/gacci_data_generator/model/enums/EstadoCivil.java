package com.gacci.gacci_data_generator.model.enums;

public enum EstadoCivil {
    SOLTEIRO(1, "Solteiro"),
    CASADO(2, "Casado"),
    DIVORCIADO(3, "Divorciado"),
    VIUVO(4, "Viúvo");

    private final int id;
    private final String label;

    EstadoCivil(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static EstadoCivil fromId(int id) {
        for (EstadoCivil e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("Id inválido para EstadoCivil: " + id);
    }
}
