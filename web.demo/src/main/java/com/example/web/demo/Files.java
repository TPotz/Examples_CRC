package com.example.web.demo;

public class Files {
    public Integer id;
    public String fileName;
    public String uuid;
    public Integer size;
    public String path;
    public String extension;

    public Files(Integer id, String fileName, String uuid, Integer size, String path, String extension) {
        this.id = id;
        this.fileName = fileName;
        this.uuid = uuid;
        this.size = size;
        this.path = path;
        this.extension = extension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "Files{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
