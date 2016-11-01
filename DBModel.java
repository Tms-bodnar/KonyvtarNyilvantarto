/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Bodnár Tamás <tms.bodnar@gmail.com> | www.kalandlabor.hu
 */
public class DBModel implements IModel{
    private Connection conn;
    
    private PreparedStatement addKonyvPstmt;
    private PreparedStatement removeKonyvPstmt;
    private PreparedStatement updateKonyvPstmt;
    private PreparedStatement getKonyvPstmt;
    private PreparedStatement getKonyvekPstmt;
    
    private PreparedStatement addTagPstmt;
    private PreparedStatement removeTagPstmt;
    private PreparedStatement updateTagPstmt;
    private PreparedStatement kolcsonzesPstmt;
    private PreparedStatement gettagPstmt;
    private PreparedStatement getTagokPstmt;
    

    public DBModel() {
    }

    public DBModel(Connection conn) throws SQLException{
        this.conn = conn;
        addKonyvPstmt = conn.prepareStatement("INSERT INTO konyvtar.konyvek (szerzo, cim, oldalszam, tagId) VALUES (?,?,?,?)");//OK
        removeKonyvPstmt = conn.prepareStatement("DELETE FROM konyvtar.konyvek WHERE id = ?");//OK
        updateKonyvPstmt = conn.prepareStatement("UPDATE konyvtar.konyvek SET szerzo = ?, cim = ?, oldalszam = ?, tagId = ? WHERE id = ?");//OK
        getKonyvPstmt = conn.prepareStatement("SELECT * FROM konyvtar.konyvek WHERE id = ?");//OK
        getKonyvekPstmt = conn.prepareStatement("SELECT * FROM konyvtar.konyvek");//OK
        addTagPstmt = conn.prepareStatement("INSERT INTO konyvtar.tagok (nev,email) VALUES (?,?)");//OK
        removeTagPstmt = conn.prepareStatement("DELETE FROM konyvtar.tagok WHERE id = ?");//OK
        updateTagPstmt = conn.prepareStatement("UPDATE konyvtar.tagok SET nev = ?, email = ? WHERE id = ?");//OK
        kolcsonzesPstmt = conn.prepareStatement("UPDATE konyvtar.konyvek SET tagId = ? WHERE id = ?");//OK
        gettagPstmt = conn.prepareStatement("SELECT * FROM konyvtar.tagok WHERE id = ?");//OK
        getTagokPstmt = conn.prepareStatement("SELECT * FROM konyvtar.tagok");//OK
        
    }
    
    @Override
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public void addKonyv(Konyv konyv) throws SQLException {
        
        addKonyvPstmt.setString(1, konyv.getSzerzo());
        addKonyvPstmt.setString(2, konyv.getCim());
        addKonyvPstmt.setInt(3, konyv.getOldalszam());
        addKonyvPstmt.setInt(4, konyv.getTagId());
        addKonyvPstmt.executeUpdate();
    }

    @Override
    public void removeKonyv(Konyv konyv) throws SQLException {
        if(konyv != null){
        removeKonyvPstmt.setInt(1, konyv.getId());
        int row = removeKonyvPstmt.executeUpdate();
        System.out.println(row);
         }else System.out.println("Nincs ilyen");
    }

    @Override
    public void updateKonyv(Konyv konyv) throws SQLException {
        if(konyv != null){
        updateKonyvPstmt.setInt(5, konyv.getId());
        updateKonyvPstmt.setString(1, konyv.getSzerzo());
        updateKonyvPstmt.setString(2, konyv.getCim());
        updateKonyvPstmt.setInt(3, konyv.getOldalszam());
        updateKonyvPstmt.setInt(4, konyv.getTagId());
        updateKonyvPstmt.executeUpdate();
        }else System.out.println("Nincs ilyen");
    }

    @Override
    public Konyv getKonyv(int id) throws SQLException {
        getKonyvPstmt.setInt(1, id);
        ResultSet rs = getKonyvPstmt.executeQuery();
        Konyv konyv = null;
        
        if (rs.next()){
            konyv = new Konyv(
                    rs.getInt("id"),
                    rs.getString("szerzo"), 
                    rs.getString("cim"), 
                    rs.getInt("oldalszam"), 
                    rs.getInt("tagId"));
        }
        rs.close();       
        return konyv;
    }

    @Override
    public Vector<Konyv> getKonyvek() throws SQLException {
        Vector<Konyv> konyvek = new Vector<>();
        ResultSet rs = getKonyvekPstmt.executeQuery();
        while ( rs.next()){
            Konyv konyv = new Konyv(
                    rs.getInt("id"),
                    rs.getString("szerzo"), 
                    rs.getString("cim"), 
                    rs.getInt("oldalszam"), 
                    rs.getInt("tagId"));
            konyvek.add(konyv);
        }
        rs.close();
        return konyvek;
    }

    @Override
    public void addTag(Tag tag) throws SQLException {
        addTagPstmt.setString(1, tag.getNev());
        addTagPstmt.setString(2, tag.getEmail());
        addTagPstmt.executeUpdate();
    }

    @Override
    public void removeTag(Tag tag) throws SQLException {
        if ( tag != null){
        removeTagPstmt.setInt(1, tag.getId());
        removeTagPstmt.executeUpdate();
        }else System.out.println("Nincs ilyen");
    }

    @Override
    public void updateTag(Tag tag) throws SQLException {
        if ( tag != null){
            updateTagPstmt.setInt(3, tag.getId());
            updateTagPstmt.setString(1, tag.getNev());
            updateTagPstmt.setString(2, tag.getEmail());
            updateTagPstmt.executeUpdate();
            }else System.out.println("Nincs ilyen");
    }

//    @Override
//    public void kolcsonzes(Konyv konyv, Tag tag) throws SQLException {
//        kolcsonzesPstmt.setInt(1, tag.getId());
//        kolcsonzesPstmt.setInt(2, konyv.getId());
//        kolcsonzesPstmt.executeUpdate();
//    }
    
    @Override
    public void vissza( Konyv konyv, Tag tag) throws SQLException{
        kolcsonzesPstmt.setInt(1, 0);
        kolcsonzesPstmt.setInt(2, konyv.getId());
        kolcsonzesPstmt.executeUpdate();
    }

    @Override
    public Tag getTag(int id) throws SQLException {
            Tag tag = null;
            gettagPstmt.setInt(1, id);
            ResultSet rs = gettagPstmt.executeQuery();
            while(rs.next()){
                tag = new Tag(
                        rs.getInt("id"),
                        rs.getString("nev"), 
                        rs.getString("email"));
            }
            rs.close();
            return tag;
    }

    @Override
    public Vector<Tag> getTagok() throws SQLException {
        Vector <Tag> tagok = new Vector();
        ResultSet rs = getTagokPstmt.executeQuery();
        while( rs.next()){
            Tag tag = new Tag(
                    rs.getInt("id"),
                    rs.getString("nev"),
                    rs.getString("email"));
            tagok.add(tag);
        }
        rs.close();
        return tagok;
    }
    
}
