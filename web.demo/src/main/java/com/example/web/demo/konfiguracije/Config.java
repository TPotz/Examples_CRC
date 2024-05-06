package com.example.web.demo.konfiguracije;

import com.example.web.demo.Constants;
import com.example.web.demo.TECAJNALISTA2Type;
import com.example.web.demo.TECAJNALISTAType;
import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.manager.TecajManager;
import com.example.web.demo.manager.TecajManagerImpl;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Configuration
@EnableScheduling
public class Config {
    @Bean
    public static void unesiConstants() throws JAXBException, IOException {
        if (Constants.verzijaLinka==1) {Constants.link="https://api.hnb.hr/tecajn/v1?format=xml";unHnb();Constants.tecajevi=unHnb();System.out.println("Unos verzije 1");}
        else {Constants.link="https://api.hnb.hr/tecajn/v2?format=xml";unHnb2();Constants.tecajevi2=unHnb2();System.out.println("\u001B[32m"+"Unos verzije 2"+"\u001B[0m");}

    }
    @Bean
    @ConditionalOnExpression("'${verzija.linka}'.equals(1)")
    public static TECAJNALISTAType unHnb() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(TECAJNALISTAType.class);
        URL url = new URL(Constants.link);
        return (TECAJNALISTAType) context.createUnmarshaller()
                .unmarshal(url);
    }
    @Bean
    @ConditionalOnExpression("'${verzija.linka}'.equals(2)")
    public static TECAJNALISTA2Type unHnb2() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(TECAJNALISTA2Type.class);
        URL url = new URL(Constants.link);
        return (TECAJNALISTA2Type) context.createUnmarshaller()
                .unmarshal(url);
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @Scheduled(cron = "0 0 15 * * FRI")///šalje mailove svaki petak u 15h
    public void scheduleWeekSend() throws SQLException, ClassNotFoundException, JRException, IOException, ParseException {
        TecajManager tec = new TecajManagerImpl();
       ArrayList<String> nazivi = tec.createJasperWeek(tec.getOpcije());//pokreće stvaranje 7 pdfova, ali i vrati nazive fileova
        tec.sendMailWeek(nazivi,javaMailSender);//za slanje mailova
    }
        //salje confirmation mailove za one usere kojima se nije poslalo jos
//    @Scheduled(fixedDelay = 50000)
//    public void scheduleConfirmEmail() throws SQLException, ClassNotFoundException, JRException, IOException, ParseException {
//        TecajDao tec = new TecajDao();
//        ArrayList<String> lista = tec.checkActivation();
//        if (lista.isEmpty()==false) {tec.sendConfirm(lista,javaMailSender);///ako ima neposlanih onda šalji
//            System.out.println("poslani mailovi!");} else {
//            System.out.println("nema mailova za poslati!");
//        }
//
//    }
}
