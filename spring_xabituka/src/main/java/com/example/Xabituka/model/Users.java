package com.example.Xabituka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor // Cria construtores automaticamente
@NoArgsConstructor
@Entity // Indica que é uma tabela no Banco de Dados
@Data // Adiciona métodos Getters, Setters, toString, equals, entre outros
public class Users {
    @Id // Indica que a variável é um ID no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Escolhe a estratégia para geração do ID
    private Long id;
    private String nickname;
    private String userType;
    @Column(name = "psswd")
    private String pw;
}
