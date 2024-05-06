package com.example.web.demo;

import com.example.web.demo.dao.TecajDao;
import com.example.web.demo.modeli.ElasticData;

import java.util.List;

public class ElasticDataTable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<ElasticData> data;  // MORA BITI 'DATA' ZA DATATABLE

    public int getDraw() {
        return draw;
    }
    public void setDraw(int draw) {
        this.draw = draw;
    }
    public int getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public int getRecordsFiltered() {
        return recordsFiltered;
    }
    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
    public List<ElasticData> getData() {
        return data;
    }
    public void setData(List<ElasticData> data) {
        this.data = data;
    }

    public static ElasticDataTable getValues(String search, int length, int start, int draw, int ord, String dir) {
        List<ElasticData> lista = null;

        System.out.println(String.format("length=%s, start=%s, getQuizDataTableByQuizUser", length, start));

        try {
            lista = TecajDao.getElasticValues(search, length, start,draw,ord,dir);

            int count = TecajDao.getcountElasticTable(search,ord);//ovjde će ići provjera getCount koliko ima stvri, sada ću samo napisati sam broj
            ElasticDataTable dt = new ElasticDataTable();
            dt.setRecordsTotal(count);
            dt.setRecordsFiltered(count);
            dt.setDraw(draw);
            dt.setData(lista);

            return dt;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
