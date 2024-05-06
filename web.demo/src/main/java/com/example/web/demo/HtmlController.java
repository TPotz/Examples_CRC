package com.example.web.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HtmlController {
    public static final String nasloviV1 = "<th>Broj tečajnice</th><th>Datum</th><th>Država</th><th>Šifra valute</th><th>Valuta</th><th>Jedinica</th><th>Kupovni tečaj</th><th>Srednji tečaj</th><th>Prodajni tečaj</th>";
    public static final String nasloviV2 = "<th>Broj tečajnice</th><th>Datum primjene</th><th>Država</th><th>Država iso</th><th>Šifra valute</th><th>Valuta</th><th>Jedinica</th><th>Kupovni tečaj</th><th>Srednji tečaj</th><th>Prodajni tečaj</th>";
    @GetMapping(value = "/hnb", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public static String welcomeAsHTML() {
        Integer verzija = 0;
        String naslovi = null;///imena varijabli (filed imena)
        List<ItemType> items = null;///lista podataka
        if (Constants.tecajevi2==null) {verzija=1;} else {verzija=2;}//provjeri verziju
        if (1==verzija) {naslovi=nasloviV1;items=Constants.tecajevi.item;} else {naslovi=nasloviV2;items=Constants.tecajevi2.item;}

        StringBuilder vrijednosti = new StringBuilder("<tr>");//stvori string za sve vrijedonsti
        for (ItemType i : items) {
            vrijednosti.append("<td>"+i.getBrojTecajnice()+"</td>");
            vrijednosti.append("<td>"+Constants.HR_DATE.format(i.getDatum())+"</td>");//da bude formatiran ispis datuma
            vrijednosti.append("<td>"+i.getDrzava()+"</td>");
            if (2==verzija) {vrijednosti.append("<td>"+i.getDrzavaIso()+"</td>");}
            vrijednosti.append("<td>"+i.getSifraValute()+"</td>");
            vrijednosti.append("<td>"+i.getValuta()+"</td>");
            vrijednosti.append("<td>"+i.getJedinica()+"</td>");
            vrijednosti.append("<td>"+i.getKupovniTecaj()+"</td>");
            vrijednosti.append("<td>"+i.getSrednjiTecaj()+"</td>");
            vrijednosti.append("<td>"+i.getProdajniTecaj()+"</td>");
            vrijednosti.append("</tr>");
        }

        return "<html>\n"+
                "<style>table, th, td {border:2px solid orange;}</style>\n"+
                "<body><h2>HNB tečajevi</h2>\n"+
                "<table style=\"width:60%\">\n"+
                "<tr>"+naslovi+"</tr>\n"+
                vrijednosti+"</table>\n"+///vrijednosti mijenjati u varijablu
                "</body></html>";
    }
    
}
