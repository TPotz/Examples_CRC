package com.example.web.demo;


import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.manager.TecajManagerImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Jasper {

    public ArrayList<String> createPdfWeek(ArrayList<String> datumi) throws JRException, IOException, ParseException, SQLException, ClassNotFoundException {
        ArrayList<String> datumiString = datumi;
        ArrayList<String> datumiFinal = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();///koristim tako da znam sve putove, na temelju toga stavljam sve u bazu informacije o fileovima
        for (int i = 1; i<6;i++) {//stvaranje liste datuma za printanje (zadnjih 5 datuma, od najstarijeg dana)
            datumiFinal.add(0,datumiString.get(datumiString.size()-i));
        }
        ArrayList<String> nazivi = new ArrayList<>();
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        JasperDesign jd = JRXmlLoader.load("C:\\Users\\Tin Potz\\JaspersoftWorkspace\\MyReports\\Blank_A4.jrxml");
        Statement st = con.createStatement();
        for (String datum : datumiFinal) {//za svaki od pet datuma napravi pdf i spremi u mapu
            JRDesignQuery query = new JRDesignQuery();
            String specQ = String.format("SELECT * FROM tecaj.lista WHERE datum = \"%s\";",datum);
            query.setText(specQ);
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(datum);
            DateFormat dtf = new SimpleDateFormat("EEE_yyyyMMdd_hhmm",Constants.HR_LOCALE);;//pretvori date u string kakav želiš
            String naziv = dtf.format(date);
            nazivi.add(naziv);//dodaj naziv u listu naziva, za ksnije slati na mail
            String path = Constants.PATH_PDFS + String.format("%s.pdf",naziv);
            paths.add(path);
            OutputStream output = new FileOutputStream(new File(path));
            JasperExportManager.exportReportToPdfStream(jp, output);
            st.executeUpdate("TRUNCATE TABLE tecaj.lista_compare;");//očisti tablicu
        }
        //stvori i compare pdf kao zadnji pdf (prvo unesi u bazu razlike da bi mogao stvoriti)
        ArrayList<Double> srednji_stari = TecajDao.getSrednji_stari();//dohvati tečajeve stare 6 dana
        TecajDao.unesiCompare(srednji_stari);//stavi u bazu stare i nove tečajeve, da ih usporediš
        jd = JRXmlLoader.load("C:\\Users\\Tin Potz\\JaspersoftWorkspace\\MyReports\\Compare_result.jrxml");
        JRDesignQuery query = new JRDesignQuery();
        String specQ = "SELECT * FROM tecaj.lista_compare ORDER BY result DESC;";
        query.setText(specQ);
        jd.setQuery(query);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
        String naziv = datumiFinal.get(0)+"-"+datumiFinal.get(datumiFinal.size()-1);
        nazivi.add(naziv);//dodaj naziv u listu naziva, za ksnije slati na mail
        String path = Constants.PATH_PDFS +String.format("%s.pdf",naziv);
        paths.add(path);
        OutputStream output = new FileOutputStream(new File(path));
        JasperExportManager.exportReportToPdfStream(jp, output);

        //dio gdje stavlja informacije u file-ovima u bazu
        for (String p : paths){
            ///za filesize
            Path put = Paths.get(p);
            long bytes = Files.size(put);

            System.out.println("ovo je vličina"+bytes);
            //za ime
            String[] spl = p.split("\\\\");
            String ime = spl[spl.length-1];
            //uuid
            UUID uuid = UUID.randomUUID();
            String uuidString = uuid.toString();
            //sredi path
            p=p.replace("\\","/");
            //dodaj u bazu
            st.executeUpdate(String.format("INSERT INTO tecaj.saved_files (id,file_name,uuid,file_size,path,extension) VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");",ime,uuidString,bytes,p,"pdf"));
        }
        st.close();
        con.close();
        return nazivi;
    }


}