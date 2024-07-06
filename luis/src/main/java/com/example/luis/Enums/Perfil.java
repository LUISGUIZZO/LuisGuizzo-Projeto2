package com.example.luis.Enums;

import lombok.Getter;

@Getter
public enum Perfil {
    ADMIN(0, "Admin"),
    CLIENTE(0, "Cliente"),
    TECNICO(0, "Técnico");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil perfil : Perfil.values()) {
            if (cod.equals(perfil.getCodigo())) {
                return perfil;
            }
        }

        throw new IllegalArgumentException("Perfil inválido");
    }
}
