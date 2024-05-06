package com.example.web.demo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static final Locale HR_LOCALE = new Locale("hr", "HR");
    public static final DateFormat HR_DATE = new SimpleDateFormat("dd.MM.yyyy");
    public static TECAJNALISTAType tecajevi;
    public static TECAJNALISTA2Type tecajevi2;
    public static String link = "";
    public static Integer verzijaLinka=2;
    public static String naslovi_tab="<th>Broj tečajnice</th><th>Datum</th><th>Država</th><th>Šifra valute</th><th>Valuta</th><th>Jedinica</th><th>Kupovni tečaj</th><th>Srednji tečaj</th><th>Prodajni tečaj</th>";
    public static String naslovi2_tab="<th>Broj tečajnice</th><th>Datum</th><th>Država</th><th>Šifra valute</th><th>Valuta</th><th>Jedinica</th><th>Kupovni tečaj</th><th>Srednji tečaj</th><th>Prodajni tečaj</th><th>Država iso</th>";
    public static final String mysqlUsername="tin";
    public static final String mysqlPass="sTannum98";
    public static final String mysqlUrl="jdbc:mysql://localhost:3307/tecaj";
    public static final String mysqlDriver="com.mysql.cj.jdbc.Driver";
    public static final String PATH_PDFS = "C:\\Users\\Tin Potz\\IdeaProjects\\web.demo\\src\\main\\webapp\\pdfs\\";
    public static final String naslovi_pdf = "<th>id</th><th>File name</th><th>Size</th><th>Path</th><th>Extension</th>";
    public static ElasticsearchClient client;

}
