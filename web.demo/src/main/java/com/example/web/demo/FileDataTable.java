package com.example.web.demo;

import com.example.web.demo.dao.TecajDao;

import java.io.File;
import java.util.List;

public class FileDataTable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<FileType> data;

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
    public List<FileType> getData() {
        return data;
    }
    public void setData(List<FileType> data) {
        this.data = data;
    }

    public static FileDataTable getFiles(String search, int length, int start, int draw, int ord, String dir) {
        List<FileType> fileList = null;

        System.out.println(String.format("length=%s, start=%s, getQuizDataTableByQuizUser", length, start));

        try {
            fileList = TecajDao.getFiles(search, length, start,draw,ord,dir);

            int count = TecajDao.getCountFilesTable(length,start,search);
            FileDataTable dt = new FileDataTable();
            dt.setRecordsTotal(count);
            dt.setRecordsFiltered(count);
            dt.setDraw(draw);
            dt.setData(fileList);

            return dt;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
