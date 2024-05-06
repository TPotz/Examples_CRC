package com.example.web.demo.adapteri;


import com.example.web.demo.Constants;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.NumberFormat;
import java.util.Date;

public class DoubleAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String s) throws Exception {
        NumberFormat format = NumberFormat.getInstance(Constants.HR_LOCALE);
        Number number = format.parse(s);
        double d = number.doubleValue();
        return d;
    }

    @Override
    public String marshal(Double aDouble) throws Exception {
        return null;
    }
}
