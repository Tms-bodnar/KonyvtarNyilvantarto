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
public class Konyv implements Serializable{
    private int id;
    private String szerzo;
    private String cim;
    private int oldalszam;
    private int tagId;

    public Konyv() {
    }
    
    public Konyv(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Konyv(String szerzo, String cim, int oldalszam, int tagId) {
        this.szerzo = szerzo;
        this.cim = cim;
        this.oldalszam = oldalszam;
        this.tagId = tagId;
    }

    
    public Konyv(int id, String szerzo, String cim, int oldalszam, int tagId) {
        this.id = id;
        this.szerzo = szerzo;
        this.cim = cim;
        this.oldalszam = oldalszam;
        this.tagId = tagId;
    }    
    
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getId() {
        return id;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public int getOldalszam() {
        return oldalszam;
    }

    public void setOldalszam(int oldalszam) {
        this.oldalszam = oldalszam;
    }

    @Override
    public String toString() {
        if (tagId == 0){
        return "ID: " + id + "; " +szerzo + ": " + cim + ", " +oldalszam + " oldalon.";
        }
        else{
            return  "ID: " +id + "; " +szerzo + ": " + cim + ", " +oldalszam + " oldalon, jelenleg nem kölcsönözhető.";
        }
    }
    
}
