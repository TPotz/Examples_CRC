package com.example.web.demo;

import com.example.web.demo.dao.TecajDao;

import java.util.List;

public class TecajDataTable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<ItemType> data;  // MORA BITI 'DATA' ZA DATATABLE

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
    public List<ItemType> getData() {
        return data;
    }
    public void setData(List<ItemType> data) {
        this.data = data;
    }

    public static TecajDataTable getImagePathDataTableByUser(String search, int length, int start, int draw, int ord, String dir) {
        // search se ne koristi

        List<ItemType> imagePathList = null;

        System.out.println(String.format("length=%s, start=%s, getQuizDataTableByQuizUser", length, start));

        try {
            imagePathList = TecajDao.getImagePathByUser(search, length, start,draw,ord,dir);

            int count = TecajDao.getCountTecajTable(length,start,search);
            TecajDataTable dt = new TecajDataTable();
            dt.setRecordsTotal(count);
            dt.setRecordsFiltered(count);
            dt.setDraw(draw);
            dt.setData(imagePathList);

            return dt;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
