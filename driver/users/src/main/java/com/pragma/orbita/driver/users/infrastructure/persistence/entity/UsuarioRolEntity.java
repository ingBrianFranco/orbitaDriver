package com.pragma.orbita.driver.users.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_rol")
public class UsuarioRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_rol", nullable = false)
    private int idUsuarioRol;
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "id_rol")
    private int idRol;

}
