package com.gacci.gacci_data_generator.model.enums;

public enum RemissaoEnum {
    EM_REMISSAO(1, "Em remissão"),
    CURADO(2, "Curado"),
    FALECIDO(3, "Falecido");

    private final int id;
    private final String label;

    RemissaoEnum(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static RemissaoEnum fromId(int id) {
        for (RemissaoEnum p : values()) {
            if (p.id == id) {
                return p;
            }
        }
        throw new IllegalArgumentException("Id inválido para Parentesco: " + id);
    }
}
