package com.example.web.demo.dao;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.web.demo.*;
import com.example.web.demo.konfiguracije.WebSecurityConfig;
import com.example.web.demo.modeli.ElasticData;
import com.example.web.demo.modeli.Intercept;
import com.example.web.demo.modeli.User;
import com.sun.codemodel.JMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TecajDao {

    public static void unesi() throws ClassNotFoundException, SQLException {///unese današnje tečaajeve
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX( id ) FROM lista;");///nađi zadnji id (potrebno za id brojeve nove tečajne liste)
        rs.next();
        Integer zadnjiID=rs.getInt(1);
        Integer i=1;
        List<ItemType> items = null;///lista podataka
        if (1==Constants.verzijaLinka) {items= Constants.tecajevi.getItem();
        } else {
            items= Constants.tecajevi2.getItem();}
        String iso;
        for (ItemType t : items) {///insertaj sve vrijednosti u bazu
            if (1==Constants.verzijaLinka) {iso="nix";} else {iso=t.getDrzavaIso();}
            String query = String.format("INSERT INTO lista VALUES (%s,%s,\"%s\",\"%s\",\"%s\",\"%s\",%s,%s,%s,%s,\"%s\");",
                    zadnjiID+i,t.getBrojTecajnice(),Constants.HR_DATE.format(t.getDatum()),t.getDrzava(),t.getSifraValute(),t.getValuta(),t.getJedinica(),t.getKupovniTecaj(),t.getSrednjiTecaj(),t.getProdajniTecaj(),iso);
            st.executeUpdate(query);
            i+=1;
        }
        st.close();
        con.close();

    }

    public static ArrayList<ItemType> conBaza() throws ClassNotFoundException, SQLException, ParseException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX( id ) FROM lista;");///nađi zadnji id (potrebno za for loop maximum)
        rs.next();
        Integer zadnjiID=rs.getInt(1);


        ResultSet rss = st.executeQuery("SELECT * FROM tecaj.lista;");//izaberi sve u bazi
        ArrayList<ItemType> itemi = new ArrayList<>();
        for (int i=0;i<zadnjiID+1;i++) {//stvara tečajne objekte češljajući bazu
            rss.next();
            ItemType item = new ItemType();
            item.setBrojTecajnice(String.valueOf(rss.getInt("broj")));
            String stt = rss.getString("datum");
            Date dat = new SimpleDateFormat("dd.MM.yyy").parse(stt);
            item.setDatum(dat);
            item.setDrzava(rss.getString("država"));
            item.setSifraValute(rss.getString("šifra_valute"));
            item.setValuta(rss.getString("valuta"));
            item.setJedinica(String.valueOf(rss.getInt("jedinica")));
            item.setKupovniTecaj(rss.getDouble("kupovni"));
            item.setSrednjiTecaj(rss.getDouble("srednji"));
            item.setProdajniTecaj(rss.getDouble("prodajni"));
            item.setDrzavaIso(rss.getString("država_iso"));
            itemi.add(item);
        }
        st.close();
        con.close();
        return itemi;
    }
    public static ArrayList<ItemType> conBaza2() throws ClassNotFoundException, SQLException, ParseException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX( id ) FROM lista;");///nađi zadnji id
        rs.next();
        Integer zadnjiID=rs.getInt(1);

        ResultSet rss = st.executeQuery("SELECT * FROM tecaj.lista;");//izaberi sve u bazi
        ArrayList<ItemType> itemi = new ArrayList<>();
        for (int i=0;i<zadnjiID;i++) {//stvara tečajne objekte češljajući bazu
            rss.next();
            ItemType item = new ItemType();
            item.setBrojTecajnice(String.valueOf(rss.getInt("broj")));
            String stt = rss.getString("datum");
            Date dat = new SimpleDateFormat("dd.MM.yyy").parse(stt);
            item.setDatum(dat);
            item.setDrzava(rss.getString("država"));
            item.setSifraValute(rss.getString("šifra_valute"));
            item.setValuta(rss.getString("valuta"));
            item.setJedinica(String.valueOf(rss.getInt("jedinica")));
            item.setKupovniTecaj(rss.getDouble("kupovni"));
            item.setSrednjiTecaj(rss.getDouble("srednji"));
            item.setProdajniTecaj(rss.getDouble("prodajni"));
            item.setDrzavaIso(rss.getString("država_iso"));
            itemi.add(item);
        }
        st.close();
        con.close();
        return itemi;
    }

    public static ArrayList<String> opcije() throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT datum FROM tecaj.lista group by datum;");///nađi zadnji id
        ArrayList<String> op = new ArrayList<>();
        while (rs.next()) {op.add(rs.getString("datum"));}
        st.close();
        con.close();
        return op;
    }
    public static ArrayList<ItemType> specificno(String datum) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rsss = st.executeQuery(String.format("SELECT datum, count(datum) FROM lista WHERE datum = \"%s\" GROUP BY datum;",datum));///nađi koliko ima unosa tog datuma
        rsss.next();
        Integer kolicina= rsss.getInt(2);

        ResultSet res = st.executeQuery(String.format("SELECT * FROM lista WHERE DATUM = \"%s\";",datum));//prikazi sve tečajeve određenog datum
        ArrayList<ItemType> itemi = new ArrayList<>();

        for (int i=0;i<kolicina;i++) {//stvara tečajne objekte češljajući bazu
            res.next();
            ItemType item = new ItemType();
            item.setBrojTecajnice(String.valueOf(res.getInt("broj")));
            String stt = res.getString("datum");
            Date dat = new SimpleDateFormat("dd.MM.yyy").parse(stt);
            item.setDatum(dat);
            item.setDrzava(res.getString("država"));
            item.setSifraValute(res.getString("šifra_valute"));
            item.setValuta(res.getString("valuta"));
            item.setJedinica(String.valueOf(res.getInt("jedinica")));
            item.setKupovniTecaj(res.getDouble("kupovni"));
            item.setSrednjiTecaj(res.getDouble("srednji"));
            item.setProdajniTecaj(res.getDouble("prodajni"));
            item.setDrzavaIso(res.getString("država_iso"));
            itemi.add(item);
        }
        st.close();
        con.close();
        return itemi;
    }



    public void sendWeekMail(ArrayList<String> nazivi, JavaMailSender javaMailSender){
        System.out.println("Šalju se mailovi...");
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            ArrayList<String> mails = getMails();
            String[] stringArray = mails.toArray(new String[0]);
            helper.setTo(stringArray);
            helper.setSubject("Tečajevi pdf");
            // default = text/plain
            //helper.setText("Check attachment for image!");
            // true = text/html
            helper.setText("<h1>Hnb tečajevi; Zadnjih 7 dana!</h1>", true);
            // hard coded a file path
            for (String naziv : nazivi) {
                String path = Constants.PATH_PDFS + String.format("%s.pdf",naziv);
                FileSystemResource file = new FileSystemResource(new File(path));
                helper.addAttachment(String.format("%s.pdf",naziv), file);
            }
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Poslano");
    }

    public static ArrayList<String> getMails() throws ClassNotFoundException, SQLException {//vrati sve mailove iz baze
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rsss = st.executeQuery("SELECT * FROM tecaj.mailovi;");
        ArrayList<String> mails = new ArrayList<>();
        while (rsss.next()) {mails.add(rsss.getString(4));}
        st.close();
        con.close();
        return mails;
    }

    public static void unesiGodinu() throws ClassNotFoundException, SQLException {///unese današnje tečaajeve
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX( id ) FROM lista_velika;");///nađi zadnji id (potrebno za id brojeve nove tečajne liste)
        rs.next();
        Integer zadnjiID=rs.getInt(1);
        Integer i=1;
        List<ItemType> items = null;///lista podataka
        if (1==Constants.verzijaLinka) {items= Constants.tecajevi.getItem();
        } else {
            items= Constants.tecajevi2.getItem();}
        String iso;
        for (ItemType t : items) {///insertaj sve vrijednosti u bazu
            if (1==Constants.verzijaLinka) {iso="nix";} else {iso=t.getDrzavaIso();}
            String query = String.format("INSERT INTO lista_velika VALUES (%s,%s,\"%s\",\"%s\",\"%s\",\"%s\",%s,%s,%s,%s,\"%s\");",
                    zadnjiID+i,t.getBrojTecajnice(),Constants.HR_DATE.format(t.getDatum()),t.getDrzava(),t.getSifraValute(),t.getValuta(),t.getJedinica(),t.getKupovniTecaj(),t.getSrednjiTecaj(),t.getProdajniTecaj(),iso);
            st.executeUpdate(query);
            i+=1;
        }
        st.close();
        con.close();

    }
    public static ArrayList<Double> getSrednji_stari() throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");//pretvori date u string kakav želiš
        ZoneId defaultZoneId = ZoneId.systemDefault();//stvara datum
        LocalDate datee = LocalDate.now().minusDays(6);
        Date date = Date.from(datee.atStartOfDay(defaultZoneId).toInstant());//uzmi datum prije 6 dana (da imaš 7day frame)
        String strDate = dateFormat.format(date);
        String query = String.format("SELECT * FROM tecaj.lista_velika WHERE datum = \"%s\" AND valuta!= \"XDR\";",strDate);
        ResultSet res = st.executeQuery(query);///izlistaj sve od prije 5 dana
        ArrayList<Double> srednji_stari = new ArrayList<>();

        while (res.next()) {srednji_stari.add(res.getDouble(9));
            System.out.println(res.getString(4));}
        System.out.println(srednji_stari);
        st.close();
        con.close();
        return srednji_stari;
    }
    public static void unesiCompare(ArrayList<Double> stari_tecajevi) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        st.executeUpdate("TRUNCATE TABLE tecaj.lista_compare;");//očisti tablicu
        List<ItemType> items;
        if (1==Constants.verzijaLinka) {items= Constants.tecajevi.getItem();
        } else {
            items= Constants.tecajevi2.getItem();}
        int i=0;
        DecimalFormat df = new DecimalFormat("#.00");
        Double res;
        for (ItemType t : items) {//dodaje sve potrebno iz današnih tečajeva i stare tečajeve, a ubaci i razliku kao double (res)
            res=(t.srednjiTecaj-stari_tecajevi.get(i))/t.srednjiTecaj*100;
            res= Double.valueOf(df.format(res));
            st.executeUpdate(String.format("INSERT INTO tecaj.lista_compare(srednji,srednji_stari,država,valuta,result) VALUES ('%s','%s','%s','%s','%s');",t.srednjiTecaj,stari_tecajevi.get(i),t.drzava,t.valuta,res));
            i++;
        }

        st.close();
        con.close();


    }
    public static ArrayList<Files> conPdfs() throws ClassNotFoundException, SQLException {//stvori table od potadaka iz tecja.saved_files
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rsss = st.executeQuery("SELECT * FROM tecaj.saved_files;");
        ArrayList<Files> objekti = new ArrayList<>();
        String path;
        while (rsss.next()) {
            path=rsss.getString(5).replace(">","/");
            Files saved = new Files(rsss.getInt(1),rsss.getString(2),rsss.getString(3),rsss.getInt(4),path,rsss.getString(6));
            objekti.add(saved);
            }
        st.close();
        con.close();
        return objekti;
    }

    public static List<ElasticData> getElasticValues(String search, int length, int start, int draw, int ord, String dir) throws ClassNotFoundException, SQLException, ParseException, IOException {
        List<ElasticData> lista =  new ArrayList<>();
        SearchResponse<ElasticData> sear = null;
        if (!search.equals("")) {
            String field = convertToField(ord);
            sear = Constants.client.search(s -> s
                            .index(".ds-logs-generic-default-2022.02.22-000001").size(2000)//ovaj size je jako bitan, inače uzme jako malo
                            .query(q -> q
								.term(t -> t
										.field(field)
										.value(v -> v.stringValue(search))

								)),///ovaj query je ako specifično želim pretraživati
                    ElasticData.class);

        } else {
            sear = Constants.client.search(s -> s
                            .index(".ds-logs-generic-default-2022.02.22-000001").size(2000),//ovaj size je jako bitan, inače uzme jako malo
                    ElasticData.class);
        }

        for (Hit<ElasticData> hit: sear.hits().hits()) {
            ElasticData el = new ElasticData();
            el.setId(hit.source().id);
            el.setUrl(hit.source().url);
            el.setParams(hit.source().params);
            el.setCookies(hit.source().cookies);
            el.setIp(hit.source().ip);
            el.setMethod(hit.source().method_name);
            el.setTimestamp(hit.source().timestamp);
            el.setUsername(hit.source().user_name);
            el.setUserAgent(hit.source().user_agent);
            el.setHostt(hit.source().hostt);
            el.setCacheControl(hit.source().cache_control);
            el.setConnection(hit.source().connection);
            el.setEncoding(hit.source().accept_encoding);
            el.setLanguage(hit.source().accept_language);
            lista.add(el);

        }
    //za orderanje
        ElasticData[] array = new ElasticData[lista.size()];
        Comparator comp = Comparator.comparing(ElasticData::getId).reversed();
        switch (ord){
            case 0:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getId)) ;//to je početni order
            case 1:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getId));
                break;
            case 2:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getUrl));
                break;
            case 3:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getMethod));
                break;
            case 4:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getId));//ne može get parrams
                break;
            case 5:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getUsername));
                break;
            case 6:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getTimestamp));
                break;
            case 7:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getConnection));
                break;
            case 8:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getCacheControl));
                break;
            case 9:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getEncoding));
                break;
            case 10:
                Arrays.sort(lista.toArray(array), Comparator.comparing(ElasticData::getLanguage));
                break;
        }
        if (dir.equals("desc")) {
            Collections.reverse(Arrays.asList(array));
        }
        length = start+length;

        try {
            System.out.println();return Arrays.stream(array).toList().subList(start,length);}//neka proba, ako ne uspije znači da nema toliko elemenata i neka onda uzme ovaj drugi return gdje je zadnji index broj elemenata
        catch (Exception e) {return Arrays.stream(array).toList().subList(start, array.length);}///ovaj kraj je za lazy load problem
    }

    public static List<ItemType> getImagePathByUser(String search, int length, int start, int draw, int ord, String dir) throws ClassNotFoundException, SQLException, ParseException {
        List<ItemType> lista =  new ArrayList<>();
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        String ordString;
        if (ord == 0){ordString="id";}
        else {ordString= String.valueOf(ord+1);}
        Integer count=0;
        ///prvo gledam imam li search dodan, ako imama onda na temelju toga šalji zahtjev
        if (!search.equals("")){
            System.out.println("Search jee pun!!!!!!111");
            String naslov = null;
            JspController cont = new JspController();
            naslov = cont.getColumnName();//pretvori broj columne u njezin naziv
            System.out.println("traži se po naslovu: "+naslov);
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM tecaj.lista_velika where %s like '%s%s' AND aktivan=1 order by %s %s limit %s,%s;", naslov, search, "%", ordString, dir, start, length));
            while (rs.next()) {//stvara tečajne objekte češljajući bazu
                ItemType item = new ItemType();
                item.setBrojTecajnice(String.valueOf(rs.getInt("broj")));
                String stt = rs.getString("datum");
                Date dat = new SimpleDateFormat("dd.MM.yyy").parse(stt);
                item.setDatum(dat);
                item.setDrzava(rs.getString("država"));
                item.setSifraValute(rs.getString("šifra_valute"));
                item.setValuta(rs.getString("valuta"));
                item.setJedinica(String.valueOf(rs.getInt("jedinica")));
                item.setKupovniTecaj(rs.getDouble("kupovni"));
                item.setSrednjiTecaj(rs.getDouble("srednji"));
                item.setProdajniTecaj(rs.getDouble("prodajni"));
                item.setDrzavaIso(rs.getString("država_iso"));
                item.setId(rs.getInt("id"));
                lista.add(item);
            }

        } else {
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM tecaj.lista_velika WHERE aktivan=1 order by %s %s limit %s,%s;", ordString, dir, start, length));
            if (ord==2){rs = st.executeQuery(String.format("SELECT * FROM tecaj.lista_velika WHERE aktivan=1 order by substring(datum,7,4) %s,substring(datum,4,2) %s,substring(datum,1,2) %s limit %s,%s;", dir, dir,dir, start, length));}
            while (rs.next()) {//stvara tečajne objekte češljajući bazu
                ItemType item = new ItemType();
                item.setBrojTecajnice(String.valueOf(rs.getInt("broj")));
                String stt = rs.getString("datum");
                Date dat = new SimpleDateFormat("dd.MM.yyy").parse(stt);
                item.setDatum(dat);
                item.setDrzava(rs.getString("država"));
                item.setSifraValute(rs.getString("šifra_valute"));
                item.setValuta(rs.getString("valuta"));
                item.setJedinica(String.valueOf(rs.getInt("jedinica")));
                item.setKupovniTecaj(rs.getDouble("kupovni"));
                item.setSrednjiTecaj(rs.getDouble("srednji"));
                item.setProdajniTecaj(rs.getDouble("prodajni"));
                item.setDrzavaIso(rs.getString("država_iso"));
                item.setId(rs.getInt("id"));
                lista.add(item);
            }

        }
        con.close();
        st.close();
        return lista;
    }

    public static Integer getCountTecajTable(int length, int start,String search) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        JspController cont = new JspController();
        String naslov = cont.getColumnName();//pretvori broj columne u njezin naziv
        String dodatak="";
        if (search.equals("")) {dodatak="";} else {
        dodatak = String.format("where %s like '%s%s'",naslov, search, "%");}
        ResultSet rs = st.executeQuery(String.format("SELECT COUNT(*) FROM tecaj.lista_velika %s;",dodatak));
        rs.next();
        int count = rs.getInt(1);
        con.close();
        st.close();
        return count;
    }
    public static Integer getcountElasticTable(String search, int ord) throws ClassNotFoundException, SQLException, IOException {
        int count =0;
        SearchResponse<ElasticData> sear = null;
        if (!search.equals("")) {
            String field = convertToField(ord);
            sear = Constants.client.search(s -> s
                            .index(".ds-logs-generic-default-2022.02.22-000001").size(2000)//ovaj size je jako bitan, inače uzme jako malo
                            .query(q -> q
                                    .term(t -> t
                                            .field(field)
                                            .value(v -> v.stringValue(search))

                                    )),///ovaj query je ako specifično želim pretraživati
                    ElasticData.class);

        } else {
            sear = Constants.client.search(s -> s
                            .index(".ds-logs-generic-default-2022.02.22-000001").size(2000),//ovaj size je jako bitan, inače uzme jako malo
                    ElasticData.class);
        }
        count=sear.hits().hits().size();
        return count;
    }
    public void crudAdd(String s, String mail) throws ClassNotFoundException, SQLException {
        String[] arr = s.split(",");
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        //traži broj redova (tako da zna id novog reda ubaciti)
        ResultSet rs = st.executeQuery("SELECT MAX(id) FROM tecaj.lista_velika");
        rs.next();
        int count = rs.getInt(1)+1;
        //sređuje državu iz vrijednosti koja je xx&xx%xx&država
        String[] sp = arr[2].split("&");
        String drz = sp[sp.length-1];
        //ubacuje novi red
        String query = String.format("INSERT INTO lista_velika (id,broj,datum,država,šifra_valute,valuta,jedinica,kupovni,srednji,prodajni,država_iso,aktivan) VALUES (%s,%s,\"%s\",\"%s\",\"%s\",\"%s\",%s,%s,%s,%s,\"%s\",1);",count,arr[0],arr[1],drz,arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9]);
        st.executeUpdate(query);
        //logaj promjenu u lista_velika_a
        String user_id = getUserIdByEmail(mail);//nabavi user_id preko maila
        query=String.format("INSERT INTO lista_velika_a(user_id,lista_velika_id,method,broj,datum,država,šifra_valute,valuta,jedinica,kupovni,srednji,prodajni,država_iso,aktivan,executed) VALUES(%s,%s,\"%s\",%s,\"%s\",\"%s\",\"%s\",\"%s\",%s,%s,%s,%s,\"%s\",1,\"%s\");",user_id,count,"INS",arr[0],arr[1],drz,arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9],getNowTimestamp());
        st.executeUpdate(query);
        con.close();
        st.close();
    }
    public void crudDel(String s, String mail) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        //logaj promjenu u lista_velika_a
        String user_id = getUserIdByEmail(mail);//nabavi user_id preko maila
        String query=String.format("INSERT INTO lista_velika_a(user_id,method,lista_velika_id,broj,datum,država,šifra_valute,valuta,jedinica,kupovni,srednji,prodajni,država_iso,aktivan,executed) SELECT %s,'DEL',id,broj,datum,država,šifra_valute,valuta,jedinica,kupovni,srednji,prodajni,država_iso,aktivan,'%s' FROM lista_velika WHERE id='%s';",user_id,getNowTimestamp(),s);
        st.executeUpdate(query);
        //briše na temelju ida
        st.executeUpdate(String.format("UPDATE tecaj.lista_velika SET aktivan=0 WHERE id = %s;",s));

        con.close();
        st.close();
    }
    public void crudChan(String s, String mail) throws ClassNotFoundException, SQLException {
        String[] arr = s.split(",");
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        //sredi drzavu
        String[] sp = arr[2].split("&");
        String drz = sp[sp.length-1];
        //updateaj
        String query = String.format("UPDATE tecaj.lista_velika\n" +
                "SET broj = %s, datum = '%s', država = \"%s\", šifra_valute = \"%s\", valuta = \"%s\", jedinica = %s, kupovni = %s, srednji = %s, prodajni = %s, država_iso = \"%s\"\n" +
                "WHERE id = %s;",arr[0],arr[1],drz,arr[3],arr[4],arr[5],arr[6],arr[7],arr[8],arr[9],arr[10]);
        st.executeUpdate(query);
        //logaj promjenu u lista_velika_a
        String user_id = getUserIdByEmail(mail);//nabavi user_id preko maila
        query=String.format("INSERT INTO lista_velika_a(user_id,method,lista_velika_id,broj,datum,država,šifra_valute,valuta,jedinica,kupovni,srednji,prodajni,država_iso,aktivan,executed) SELECT %s,'UPD',id,broj,datum,država,šifra_valute,valuta,jedinica,kupovni,srednji,prodajni,država_iso,aktivan,'%s' FROM lista_velika WHERE id='%s';",user_id,getNowTimestamp(),arr[10]);
        st.executeUpdate(query);
        con.close();
        st.close();
    }
    public static List<FileType> getFiles(String search, int length, int start, int draw, int ord, String dir) throws ClassNotFoundException, SQLException, ParseException {
        List<FileType> lista =  new ArrayList<>();
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM tecaj.saved_files;");
        while (rs.next()) {//stvara files objekte češljajući bazu
            FileType item = new FileType();
            item.setId(rs.getInt("id"));
            item.setFileName(rs.getString("file_name"));
            item.setUuid(rs.getString("uuid"));
            item.setFileSize(rs.getInt("file_size"));
            item.setPath(rs.getString("path"));
            item.setExtension(rs.getString("extension"));
            lista.add(item);
        }
        con.close();
        st.close();
        return lista;
    }
    public static Integer getCountFilesTable(int length, int start,String search) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        JspController cont = new JspController();
        String naslov = cont.getColumnName();//pretvori broj columne u njezin naziv
        String dodatak="";
        if (search.equals("")) {dodatak="";} else {
            dodatak = String.format("where %s like '%s%s'",naslov, search, "%");}
        ResultSet rs = st.executeQuery(String.format("SELECT COUNT(*) FROM tecaj.saved_files %s;",dodatak));
        rs.next();
        int count = rs.getInt(1);
        con.close();
        st.close();
        return count;
    }
    public String getInfo(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(String.format("SELECT * FROM tecaj.lista_velika WHERE id=%s;",id));
        rs.next();
        StringBuilder sb = new StringBuilder();
        sb.append(rs.getInt("broj")+"&");
        sb.append(rs.getString("datum")+"&");
        sb.append(rs.getString("država")+"&");
        sb.append(rs.getString("šifra_valute")+"&");
        sb.append(rs.getString("valuta")+"&");
        sb.append(rs.getInt("jedinica")+"&");
        sb.append(rs.getDouble("kupovni")+"&");
        sb.append(rs.getDouble("srednji")+"&");
        sb.append(rs.getDouble("prodajni")+"&");
        sb.append(rs.getString("država_iso"));
        con.close();
        st.close();
        return sb.toString();

    }
    public String getEditOpcije() throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT država,šifra_valute,valuta,država_iso FROM tecaj.lista_velika group by država;");
        StringBuilder sb = new StringBuilder();
        String s;
        while(rs.next()){
            s=String.format("<option value='%s&%s&%s&%s'>%s</option>",rs.getString("šifra_valute"),rs.getString("valuta"),rs.getString("država_iso"),rs.getString("država"),rs.getString("država"));
            sb.append(s);
            JspController.editDrzave+=String.format("\"%s\",",rs.getString("valuta"));
        }
        con.close();
        st.close();
        return sb.toString();
    }

    public ArrayList<User>getUsers() throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM tecaj.web_acess;");
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()){///knostruktor počinje s emailom zato što se promijenilo tako da je email upucaš u username varijablu model User
            User u = new User(rs.getString("email"),rs.getString("password"),rs.getString("role"),rs.getInt("locked"));
            users.add(u);
        }
        con.close();
        st.close();
        return users;
    }
    public String checkRegister(String e) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        String res="free";
        ArrayList<String> lista = new ArrayList<>();//generička lisa u koju stavljam imena ili emailove
        //provjera emailova
        ResultSet rs = st.executeQuery("SELECT email FROM tecaj.web_acess;");
        while (rs.next()){lista.add(rs.getString("email"));}
        if (lista.contains(e)){res="Email already exists!";}
        con.close();
        st.close();
        return res;

    }

    public void createMailActivation(String uuid) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        //unesi user_id, uuid, created timestamp u mail_activation tablicu (user id će prepoznati preko joina s web_acces)
        String query = String.format("INSERT INTO tecaj.mail_activation(user_id,created) SELECT w.user_id,'%s' FROM tecaj.web_acess w WHERE w.uuid ='%s'",timestamp,uuid);
        st.executeUpdate(query);
        con.close();
        st.close();

    }

    public ArrayList<String> checkActivation() throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ArrayList<String> lista = new ArrayList<>();///ovo je lista uuid-ova...ako nema neposlanih onda će ostati prazna
        //provjeri ima li neposlanih
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM mail_activation WHERE sent IS NULL;");
        rs.next();
        Integer count = rs.getInt(1);
        //ako ima stvori listu njihovih uuid-ova
        if (count!=0){
            rs=st.executeQuery("SELECT user_id FROM tecaj.mail_activation WHERE sent IS NULL;");
            while(rs.next()){lista.add(rs.getString("user_id"));}//dodaj svaki neposlani uuid
        }
        con.close();
        st.close();
        return lista;
    }

    public void sendConfirm(ArrayList<String> ids, JavaMailSender javaMailSender) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        ResultSet rs = null;
        String mail = "";
        String html = "";///obrađena forma
        String name="";
        String uuid="";

        String query = "SELECT email,user_name,last_name,uuid FROM tecaj.web_acess WHERE user_id=\"%s\";";
        for (String s : ids) {
            System.out.println("Šalje se confirmation mail za potrvdu registracije uuida: "+s);
            try {
                MimeMessage msg = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(msg, true);
                //dobij mail i username od uuida (username bitan za personalizaciju maila)
                rs = st.executeQuery(String.format(query,s));
                rs.next();
                mail=rs.getString("email");
                name=rs.getString("user_name")+" "+rs.getString("last_name");
                uuid=rs.getString("uuid");
                helper.setTo(mail);
                helper.setSubject("Registration confirmation!");
                //dodajem timestamp
                Date today = new Date();
                Timestamp timestamp = new Timestamp(today.getTime());
                st.executeUpdate(String.format("UPDATE tecaj.mail_activation SET sent = '%s' WHERE user_id = '%s';",timestamp,s));
                html = Designer.createHtmlConfirm(uuid,name);//ovdje se stvara html tako da ubacim parametre uuid i ime

                helper.setText(html, true);

                javaMailSender.send(msg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            System.out.println("Poslano za "+s);
        }
    }

    ///ovdje je parameter e zapravo uuid
    public String activateMail(String e) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        String query = String.format("UPDATE tecaj.web_acess SET locked = 0 WHERE uuid = '%s';",e);
        st.executeUpdate(query);
        query = String.format("SELECT user_id from tecaj.web_acess WHERE uuid = '%s';",e);
        ResultSet rs = st.executeQuery(query);
        rs.next();String id = rs.getString("user_id");
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        query = String.format("UPDATE tecaj.mail_activation SET used = '%s' WHERE user_id = '%s';",timestamp,id);
        st.executeUpdate(query);

        con.close();
        st.close();
        String res = String.format("Account with email %s confirmed!",e);
        return res;
    }
    public String createUser(String u,String l, String p, String e) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String query = String.format("INSERT INTO tecaj.web_acess(user_name,last_name,password,role,locked,email,uuid) VALUES ('%s', '%s', '%s', '%s',%s, '%s', '%s'); ",u,l,p,"USER",1,e,uuidString);
        st.executeUpdate(query);
        con.close();
        st.close();
        return uuidString;
    }
    public User getUserByUuid(String e) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        String query = String.format("SELECT * FROM tecaj.web_acess WHERE uuid='%s';",e);
        ResultSet rs = st.executeQuery(query);
        rs.next();///konstruktor počinje s emailom zato što je mail zapravo username
        User u = new User(rs.getString("email"),rs.getString("password"),rs.getString("role"),rs.getInt("locked"));

        con.close();
        st.close();

        return u;
    }
    public String getFullName(String e) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        String query = String.format("SELECT * FROM tecaj.web_acess WHERE email='%s';",e);
        ResultSet rs = st.executeQuery(query);
        rs.next();///konstruktor počinje s emailom zato što je mail zapravo username
        String fullName = rs.getString("user_name")+" "+rs.getString("last_name");
        con.close();
        st.close();

        return fullName;
    }
    public String getUserIdByEmail(String e) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        String query = String.format("SELECT user_id FROM tecaj.web_acess WHERE email=\"%s\";",e);
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String user_id = rs.getString("user_id");
        con.close();
        st.close();

        return user_id;
    }
    public Timestamp getNowTimestamp(){
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        return timestamp;
    }

    public void logIntercept(Intercept cept) throws ClassNotFoundException, SQLException {
        Class.forName(Constants.mysqlDriver);
        Connection con = DriverManager.getConnection(Constants.mysqlUrl,Constants.mysqlUsername,Constants.mysqlPass);
        Statement st = con.createStatement();
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        //unesi user_id, uuid, created timestamp u mail_activation tablicu (user id će prepoznati preko joina s web_acces)
        String query = String.format("INSERT INTO tecaj.request_logs(url,params,method_name,user_name,timestamp,cookies,ip,hostt,user_agent,connection,cache_control,accept_encoding,accept_language) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"
                ,cept.getUrl(),cept.getParams(),cept.getMethod(),cept.getUsername(),timestamp,cept.getCookies(),cept.getIp(),cept.getHost(),cept.getUserAgent(),cept.getConnection(),cept.getCacheControl(),cept.getEncoding(),cept.getLanguage());
        try {st.executeUpdate(query);} catch (Exception e) {
            System.out.println("Request prevelik pa se nije spremio: "+ query);//nekad ne valja query ili je prevelik pa bolje da printa nego da faila
        }
        con.close();
        st.close();

    }
    public static String convertToField(int column){
        String res="";
        switch (column){
            case 1:
                res="id";
                break;
            case 2:
                res="url";
                break;
            case 3:
                res="method_name";
                break;
            case 4:
                res="params";
                break;
            case 5:
                res="user_name";
                break;
            case 6:
                res="timestamp";
                break;
        }
        return res;
    }

}
