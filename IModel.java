/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Bodnár Tamás <tms.bodnar@gmail.com> | www.kalandlabor.hu
 */
public interface IModel {
    void close() throws SQLException;
    
    void addKonyv(Konyv konyv) throws SQLException;
    void removeKonyv(Konyv konyv) throws SQLException;
    void updateKonyv(Konyv konyv) throws SQLException;
    Konyv getKonyv(int id) throws SQLException;
    Vector<Konyv> getKonyvek() throws SQLException;
    
    void addTag(Tag tag) throws SQLException;
    void removeTag(Tag tag) throws SQLException;
    void updateTag(Tag tag) throws SQLException;
//    void kolcsonzes(Konyv konyv, Tag tag) throws SQLException;
    void vissza(Konyv konyv, Tag tag) throws SQLException;
    Tag getTag(int id) throws SQLException;
    Vector<Tag> getTagok() throws SQLException;
    
}
