package com.example.luis.Chamado;


import com.example.luis.Cliente.Cliente;
import com.example.luis.Enums.Prioridade;
import com.example.luis.Enums.Status;
import com.example.luis.Tecnico.Tecnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern ="dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern ="dd/MM/yyyy")
    private LocalDate dataFechamento;

    private Prioridade prioridade;

    private Status status;

    private String titulo;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado(ChamadoDTO obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.prioridade = Prioridade.toEnum(obj.getPrioridade());
        this.status = Status.toEnum(obj.getStatus());
    }
}