/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Bodnár Tamás <tms.bodnar@gmail.com> | www.kalandlabor.hu
 */
public class Tag implements Serializable{
    private int id;
    private String nev;
    private String email;

    public Tag() {
    }

    public Tag(String nev, String email) {
        this.nev = nev;
        this.email = email;
    }

    public Tag(int id, String nev, String email) {
        this.id = id;
        this.nev = nev;
        this.email = email;
    } 
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return "ID: " +id + ";" +nev + ", e-mail címe: " + email;
    }
    
    
}
