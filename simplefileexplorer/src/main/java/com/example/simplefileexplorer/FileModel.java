package com.example.simplefileexplorer;

class FileModel {
    private String absolutePath;
    private FileModelType fileModelType;

    public FileModel(String absolutePath, FileModelType fileModelType) {
        this.absolutePath = absolutePath;
        this.fileModelType = fileModelType;
    }


    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public FileModelType getFileModelType() {
        return fileModelType;
    }

    public void setFileModelType(FileModelType fileModelType) {
        this.fileModelType = fileModelType;
    }
}
