package com.example.web.demo.adapteri;




import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {


    @Override
    public Date unmarshal(String s) throws Exception {
        Date date;
        String format;
        if (s.contains("-")) {format="yyyy-MM-dd";} else {format="dd.MM.yyyy";}
        return new SimpleDateFormat(format).parse(s);}


    @Override
    public String marshal(Date date) throws Exception {
        return null;
    }




}
