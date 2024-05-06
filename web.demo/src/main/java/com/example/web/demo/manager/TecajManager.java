package com.example.web.demo.manager;

import com.example.web.demo.Files;
import com.example.web.demo.ItemType;
import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.modeli.User;
import net.sf.jasperreports.engine.JRException;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface TecajManager {
    ArrayList<ItemType> getBaza() throws SQLException, ParseException, ClassNotFoundException;
    ArrayList<String> getOpcije() throws SQLException, ClassNotFoundException;
    StringBuilder getDesignOpc(ArrayList<String> s);
    ArrayList<String> createJasperWeek(ArrayList<String> datumi) throws JRException, SQLException, IOException, ParseException, ClassNotFoundException;
    void sendMailWeek(ArrayList<String> nazivi, JavaMailSender javaMailSender);
    ArrayList<Files> getPdfs() throws ClassNotFoundException, SQLException;
    String addToVelika(String s, String mail) throws SQLException, ClassNotFoundException;
    String delToVelika(String s, String mail) throws SQLException, ClassNotFoundException;
    String chanToVelika(String s, String mail) throws SQLException, ClassNotFoundException;
    String getInfo(Integer id) throws SQLException, ClassNotFoundException;
    String getEditOpcije() throws SQLException, ClassNotFoundException;
    ArrayList<User> getUsers() throws SQLException, ClassNotFoundException;
    String checkRegister(String e) throws SQLException, ClassNotFoundException;
    String activateMail(String e) throws SQLException, ClassNotFoundException;
}
