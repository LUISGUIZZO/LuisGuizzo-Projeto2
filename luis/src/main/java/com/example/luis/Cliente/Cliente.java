package com.example.luis.Cliente;


import com.example.luis.Chamado.Chamado;
import com.example.luis.Enums.Perfil;
import com.example.luis.Pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        super(obj.getId(), obj.getNome(), obj.getCpf(), obj.getEmail(), obj.getSenha());
        addPerfil(Perfil.CLIENTE);
    }
}
