package com.project.pins.entities;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PINES")
public class PinEntity implements Serializable {

    private static long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pin")
    private Long idPin;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_pin", unique = true)
    private String nombrePin;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fondo_pin")
    private String fondoPin;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "img_pin")
    private String imgPin;

    @NotNull
    @Column(name = "estado_pin", columnDefinition = "boolean")
    private Boolean estadoPin;
}
