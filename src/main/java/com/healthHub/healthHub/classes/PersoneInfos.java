package com.healthHub.healthHub.classes;

public class PersoneInfos {
    private int idPersone;
    private String fullName;

    public int getIdPersone() {
        return idPersone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setIdPersone(int idPersone) {
        this.idPersone = idPersone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PersoneInfos(int idPersone, String fullName) {
        this.idPersone = idPersone;
        this.fullName = fullName;
    }
}
