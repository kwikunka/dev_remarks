package com.kwikunka.List_todo.model;

import javax.persistence.*;
/**
 * Created by kwik on 05.02.2017.
 */
@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @Column(name = "idTODO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTODO;

    @Column(name = "nameTODO")
    private String nameTODO;

    @Column(name = "isCompleted")
    private boolean isCompleted;

    public int getIdTODO() {
        return idTODO;
    }

    public void setIdTODO(int idTODO) {
        this.idTODO = idTODO;
    }

    public String getNameTODO() {
        return nameTODO;
    }

    public void setNameTODO(String nameTODO) {
        this.nameTODO = nameTODO;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "idTODO=" + idTODO +
                ", nameTODO='" + nameTODO + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
