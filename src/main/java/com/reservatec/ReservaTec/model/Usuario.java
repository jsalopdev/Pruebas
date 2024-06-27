package com.reservatec.ReservaTec.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "usuario")
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_usuario")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "nombres", nullable = false)
    private String name;

    @Column(name = "codigo_tecsup")
    private String codigoTecsup;
}