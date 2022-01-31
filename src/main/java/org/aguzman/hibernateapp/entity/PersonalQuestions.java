package org.aguzman.hibernateapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tb_personal")
public class PersonalQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namechildren;
    private Integer numberchildren;

    public PersonalQuestions() {
    }

    public PersonalQuestions(String calle, Integer numero) {
        this.namechildren = calle;
        this.numberchildren = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return namechildren;
    }

    public void setCalle(String calle) {
        this.namechildren = calle;
    }

    public Integer getNumero() {
        return numberchildren;
    }

    public void setNumero(Integer numero) {
        this.numberchildren = numero;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", namechildren='" + namechildren + '\'' +
                ", numberchildren=" + numberchildren +
                '}';
    }
}
