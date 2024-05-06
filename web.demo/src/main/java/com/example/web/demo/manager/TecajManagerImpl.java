package com.example.web.demo.manager;

import com.example.web.demo.Designer;
import com.example.web.demo.Files;
import com.example.web.demo.ItemType;
import com.example.web.demo.Jasper;
import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.modeli.User;
import net.sf.jasperreports.engine.JRException;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class TecajManagerImpl implements TecajManager {
    public ArrayList<ItemType> getBaza() throws SQLException, ParseException, ClassNotFoundException {
        return TecajDao.conBaza();
    }
    public ArrayList<String> getOpcije() throws SQLException, ClassNotFoundException {
        return TecajDao.opcije();
    }
    public StringBuilder getDesignOpc(ArrayList<String> s) {
        Designer des = new Designer();
        return des.designOpcije(s);
    }
    public ArrayList<String> createJasperWeek(ArrayList<String> datumi) throws JRException, SQLException, IOException, ParseException, ClassNotFoundException {
        Jasper jasp = new Jasper();
        return jasp.createPdfWeek(datumi);//vraća nazive; arraylistu naziva
    }
    public void sendMailWeek(ArrayList<String> nazivi, JavaMailSender javaMailSender) {
        TecajDao tec = new TecajDao();
        tec.sendWeekMail(nazivi, javaMailSender);
    }
    public ArrayList<Files> getPdfs() throws ClassNotFoundException, SQLException {
        return TecajDao.conPdfs();
    }
    public String addToVelika(String s, String mail) throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        tec.crudAdd(s,mail);
        return "Uneseno!";
    }
    public String delToVelika(String s, String mail) throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        tec.crudDel(s,mail);
        return "Obrisano!";
    }
    public String chanToVelika(String s, String mail) throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        tec.crudChan(s,mail);
        return "Promijenjeno!";
    }
    public String getInfo(Integer id) throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        return tec.getInfo(id);
    }
    public String getEditOpcije() throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        return tec.getEditOpcije();
    }
    public ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        return tec.getUsers();
    }
    public String checkRegister(String e) throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        return tec.checkRegister(e);
    }
    public String activateMail(String e) throws SQLException, ClassNotFoundException {
        TecajDao tec = new TecajDao();
        return tec.activateMail(e);
    }
}
