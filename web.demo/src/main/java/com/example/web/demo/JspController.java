package com.example.web.demo;

import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.konfiguracije.Config;
import com.example.web.demo.konfiguracije.WebSecurityConfig;
import com.example.web.demo.manager.TecajManager;
import com.example.web.demo.manager.TecajManagerImpl;
import com.example.web.demo.modeli.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class JspController {
    public static Integer colSelect;
    public static String editDrzave="";//kao pomoć pri upisivanju podataka kod buttona za edit (napuni se selection za drzavu)

    @GetMapping(value ="/danas")
    public String next(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        //TecajDao.unesi(); ///uključim ako želim staviti današnje tečajeve u bazu
        //TecajDao.unesiGodinu();///za unos cijele godine podataka
        List<ItemType> items = null;///lista podataka
        if (1==Constants.verzijaLinka) {items=Constants.tecajevi.item;model.put("naslovi",Constants.naslovi_tab);} else {items=Constants.tecajevi2.item;model.put("naslovi",Constants.naslovi2_tab);}

        model.put("inputLista",items);

        return "hnb";//ime jsp file-a
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping(value ="/baza")//nastavak na  serveru-prikaz baze
    public String baza(Map<String, Object> model) throws SQLException, ClassNotFoundException, ParseException, JRException, IOException {
        System.setProperty("java.awt.headless", "false");///radi exception-a kojeg jasper radi
        TecajManager tec = new TecajManagerImpl();
     //ArrayList<String> nazivi = tec.createJasperWeek(tec.getOpcije());//pokreće stvaranje 7 pdfova, ali i vrati nazive fileova
        //tec.sendMailWeek(nazivi,javaMailSender);//za slanje mailova
        model.put("naslovi",Constants.naslovi_tab+"<th>Država iso</th>");///stavlja naslove u html tablicu
        model.put("inputLista",tec.getBaza());
        model.put("select",tec.getDesignOpc(tec.getOpcije()));
        return "hnb";//ime jsp file-a
    }
    @GetMapping(value ="/json")
    public String welcome(Map<String, Object> model) throws SQLException, ClassNotFoundException, ParseException {
        //poveznica s bazom
        ArrayList<ItemType> podaci = TecajDao.conBaza();
        Gson gson = new GsonBuilder()//stvara json od svih objekata iz baze
                .setPrettyPrinting()
                .create();
        String jsonString = gson.toJson(podaci);

        model.put("jsonLista",jsonString);
        return "asJson";
    }

    @GetMapping("/spec")
    @ResponseBody
    public String getFoos(@RequestParam String op) throws SQLException, ClassNotFoundException, ParseException {
        ArrayList<ItemType> podaci = TecajDao.specificno(op);
        StringBuilder vrijednosti = new StringBuilder("<tr>");//stvori string za sve vrijedonsti
        for (ItemType i : podaci) {
            vrijednosti.append("<td>"+i.getBrojTecajnice()+"</td>");
            vrijednosti.append("<td>"+Constants.HR_DATE.format(i.getDatum())+"</td>");//da bude formatiran ispis datuma
            vrijednosti.append("<td>"+i.getDrzava()+"</td>");
            vrijednosti.append("<td>"+i.getSifraValute()+"</td>");
            vrijednosti.append("<td>"+i.getValuta()+"</td>");
            vrijednosti.append("<td>"+i.getJedinica()+"</td>");
            vrijednosti.append("<td>"+i.getKupovniTecaj()+"</td>");
            vrijednosti.append("<td>"+i.getSrednjiTecaj()+"</td>");
            vrijednosti.append("<td>"+i.getProdajniTecaj()+"</td>");
            vrijednosti.append("<td>"+i.getDrzavaIso()+"</td>");
            vrijednosti.append("</tr>");
        }
        return "<thead><tr>"+Constants.naslovi2_tab+"</tr></thead>\n"+
                vrijednosti;
    }

    @GetMapping("/jasp")
    @ResponseBody
    public byte[] download(@RequestParam String dat) throws SQLException, ClassNotFoundException, ParseException, JRException, IOException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        JasperDesign jd = JRXmlLoader.load("C:\\Users\\Tin Potz\\JaspersoftWorkspace\\MyReports\\Blank_A4.jrxml");
        JRDesignQuery query= new JRDesignQuery();
        String specQ = String.format("SELECT * FROM tecaj.lista WHERE datum = \"%s\";",dat);
        query.setText(specQ);
        jd.setQuery(query);
        JasperReport jr= JasperCompileManager.compileReport(jd);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        OutputStream output = new FileOutputStream(new File("C:\\Users\\Tin Potz\\Desktop\\HNB-baza.pdf"));

        JasperExportManager.exportReportToPdfStream(jp, output);
        byte[] array = Files.readAllBytes(Paths.get("C:\\Users\\Tin Potz\\Desktop\\HNB-baza.pdf"));

        //JasperViewer.viewReport(jp);
        return array;
    }
    @GetMapping(value ="/pdf")
    public String tako(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String whole = principal.toString();
        Pattern pattern = Pattern.compile("Username=(.+?),");
        Matcher matcher = pattern.matcher(whole);
        if (matcher.find()){
            //dobavi ime i prezime preko maila
            TecajDao tec = new TecajDao();
            String fName = tec.getFullName(matcher.group(1));
        model.put("fullname",fName);}



        return "views/pdf_preview";//ime jsp file-a
    }

    @GetMapping(value ="/tab-home")
    public String home(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        return "views/home";
    }
    @GetMapping(value ="/tab-codebook")
    public String codebook(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        System.setProperty("java.awt.headless", "false");///radi exception-a kojeg jasper radi
        TecajManager tec = new TecajManagerImpl();
       /* ArrayList<String> nazivi = tec.createJasperWeek(tec.getOpcije());//pokreće stvaranje 7 pdfova, ali i vrati nazive fileova
        tec.sendMailWeek(nazivi,javaMailSender);*///za slanje mailova
        model.put("naslovi",Constants.naslovi_tab+"<th>Država iso</th>");///stavlja naslove u html tablicu
        model.put("inputLista",tec.getBaza());
        model.put("opcije",tec.getEditOpcije());
        model.put("editDrzave",editDrzave.substring(0, editDrzave.length() - 1));///brise se zadnji zared iz edit drzave i salje se

        return "views/table";
    }
    @GetMapping(value ="/tab-pdfs")
    public String pdfs(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {


        return "views/pdf_table";
    }
    @GetMapping(value ="/tab-elastic")
    public String elas(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        System.out.println("u get mappingu sam od tab-elastic");
        return "views/elasticTable";
    }

    @GetMapping(value ="/login")
    public String log(Map<String, Object> model) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException, NoSuchMethodException {

        return "login";
    }
    @RequestMapping(value ="/jsonFile",method = RequestMethod.GET)
    @ResponseBody
    public TecajDataTable tecajData( @RequestParam("length") int length, // limit
                      @RequestParam("start") int start,   // offset
                      @RequestParam("draw") int draw,    // request id
                      @RequestParam("search[value]") String search,
                      @RequestParam("order[0][column]") int ord,
                      @RequestParam("order[0][dir]") String dir              //direction of ordering
    ) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {

        colSelect=ord;///da zapamti program koji stup sam izabrao


        TecajDataTable tecajData = TecajDataTable.getImagePathDataTableByUser(search, length, start,draw,ord,dir);
        return tecajData;
    }
    public String getColumnName(){
        String naslov = "";
        switch (colSelect) {
            case 1:
                naslov="broj";
                break;
            case 2:
                naslov="datum";
                break;
            case 3:
                naslov="država";
                break;
            case 4:
                naslov="šifra_valute";
                break;
            case 5:
                naslov="valuta";
                break;
            case 6:
                naslov="jedinica";
                break;
            case 7:
                naslov="kupovni";
                break;
            case 8:
                naslov="srednji";
                break;
            case 9:
                naslov="prodajni";
                break;
            case 10:
                naslov="država_iso";
                break;

        }
        return naslov;
    }
    @RequestMapping(value ="/crudTable", method = RequestMethod.POST)
    @ResponseBody///paramettri: s je string koji te se poslao u obliku forme, a c je check što da se napravi s poslanim:add,del ili chan
    public String crud(@RequestParam String s,
                       @RequestParam String c) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        String res="";
        //nađi koji je to user napravio i pošalji i njegov mail
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String whole = principal.toString();
        Pattern pattern = Pattern.compile("Username=(.+?),");
        Matcher matcher = pattern.matcher(whole);
        String mail="";
        if (matcher.find()){
            mail=matcher.group(1);
        }

        TecajManagerImpl manager = new TecajManagerImpl();
        switch (c){
            case "add":
                res=manager.addToVelika(s,mail);
                break;
            case "del":
                res=manager.delToVelika(s,mail);
                break;
            case "chan":
                res=manager.chanToVelika(s,mail);
                break;


        }
        return res;
    }
    @RequestMapping(value ="/filesTable",method = RequestMethod.GET)
    @ResponseBody
    public FileDataTable fileData( @RequestParam("length") int length, // limit
                                     @RequestParam("start") int start,   // offset
                                     @RequestParam("draw") int draw,    // request id
                                     @RequestParam("search[value]") String search,
                                     @RequestParam("order[0][column]") int ord,
                                     @RequestParam("order[0][dir]") String dir              //direction of ordering
    ) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {

        colSelect=ord;///da zapamti program koji stup sam izabrao


        FileDataTable filesData = FileDataTable.getFiles(search, length, start,draw,ord,dir);
        return filesData;
    }
    @GetMapping(value ="/edit")
    @ResponseBody
    public String ed(@RequestParam Integer i) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        String res="";
        TecajManagerImpl manager = new TecajManagerImpl();
        res=manager.getInfo(i);
        return res;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String re(@RequestParam("u") String u,
                   @RequestParam("l") String l,
                   @RequestParam("p") String p,
                   @RequestParam("e") String e) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        String res="";
        System.out.println("sad sam u registeru ddaaaa");
        System.out.println(u+p+e);
        TecajManagerImpl manager = new TecajManagerImpl();
        res=manager.checkRegister(e);
        //ako ne postoji taj email, onda stvori takvog usera u bazi, stavi mu da je locked 1, i pošalji mu mail za konfirmaciju, da se odključa
        if (res=="free"){res="The link for your registration confirmation will be sent shortly on your email.";
            //stvori usera u bazi
            TecajDao tec = new TecajDao();
            String uuid = tec.createUser(u,l,p,e);
            //zatim ga dodaj u tablicu aktivacije
            tec.createMailActivation(uuid);
            }
        return res;
    }
    @GetMapping("/confirmEmail")
    public String conf(@RequestParam(name="email", required=false, defaultValue="World") String email, Map<String, Object> model) {
        System.out.println("ovo je string email: "+email);
        model.put("email",email);
        return "/confirm";
    }
    @Autowired
    ApplicationContext context;

    @GetMapping("/activateEmail")//parametar e je zapravo uuid usera koji se želu unlockati
    public String active(@RequestParam(name="uuid", required=false, defaultValue="World") String uuid) throws SQLException, ClassNotFoundException {
        String res="";
        TecajManagerImpl manager = new TecajManagerImpl();
        res=manager.activateMail(uuid);
        //stvori usera in memory
        TecajDao tec = new TecajDao();
        User u = tec.getUserByUuid(uuid);
        org.springframework.security.core.userdetails.User.UserBuilder users = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager h = context.getBean(InMemoryUserDetailsManager.class);
        //probaj stvoriti osobu, ako ne ide (što znači da već postoji, onda ju obriši i napravi novu)
        try{
        h.createUser(users.username(u.getUserName()).password(u.getPassword()).roles(u.getRole()).accountLocked(false).build());}
        catch (Exception e){h.deleteUser(u.getUserName());h.createUser(users.username(u.getUserName()).password(u.getPassword()).roles(u.getRole()).accountLocked(false).build());}

        return "/success";
    }
    @GetMapping("/registration")
    public String reg(){
        return "/register";
    }

    @GetMapping("/messages")
    @ResponseBody
    public String getMessage() {
        return "Hello from Docker!";
    }

    @RequestMapping(value = "/loginLog", method = RequestMethod.POST)
    @ResponseBody
    public void ll(@RequestParam("u") String u,
                     @RequestParam("p") String p) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        String res="";
        System.out.println("sad sam u login logu");
        System.out.println("ovov je username:"+u);
        System.out.println("ovov je pass:"+p);

    }

    @RequestMapping(value = "/logoutLog", method = RequestMethod.POST)
    @ResponseBody
    public void lol(@RequestParam("u") String u) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String whole = principal.toString();
        Pattern pattern = Pattern.compile("Username=(.+?),");
        Matcher matcher = pattern.matcher(whole);
        if (matcher.find()){
        System.out.println("ovaj user se log outao:" +matcher.group(1));}


    }

    @RequestMapping(value ="/elastic",method = RequestMethod.GET)
    @ResponseBody
    public ElasticDataTable elData( @RequestParam("length") int length, // limit
                                     @RequestParam("start") int start,   // offset
                                     @RequestParam("draw") int draw,    // request id
                                     @RequestParam("search[value]") String search,
                                     @RequestParam("order[0][column]") int ord,
                                     @RequestParam("order[0][dir]") String dir              //direction of ordering
    ) throws SQLException, ClassNotFoundException, IOException, JRException, ParseException {

        colSelect=ord;///da zapamti program koji stup sam izabrao


        ElasticDataTable elasticData = ElasticDataTable.getValues(search, length, start,draw,ord,dir);
        return elasticData;
    }



}
