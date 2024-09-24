package com.gacci.gacci_data_generator.model.enums;

public enum Parentesco {
    PAI(1, "Pai"),
    MAE(2, "Mãe"),
    FILHO(3, "Filho"),
    FILHA(4, "Filha"),
    OUTRO(5, "Outro");

    private final int id;
    private final String label;

    Parentesco(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Parentesco fromId(int id) {
        for (Parentesco p : values()) {
            if (p.id == id) {
                return p;
            }
        }
        throw new IllegalArgumentException("Id inválido para Parentesco: " + id);
    }
}
