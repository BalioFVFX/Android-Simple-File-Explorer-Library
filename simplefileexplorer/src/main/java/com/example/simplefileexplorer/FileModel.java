package com.example.simplefileexplorer;

class FileModel {
    private String absolutePath;
    private String directoryPath;
    private FileModelType fileModelType;
    private boolean isSelected;

    public FileModel(String absolutePath, FileModelType fileModelType) {
        this.absolutePath = absolutePath;
        this.directoryPath = this.absolutePath;
        this.fileModelType = fileModelType;
        this.isSelected = false;
    }

    public FileModel(String absolutePath, String directoryPath, FileModelType fileModelType) {
        this(absolutePath, fileModelType);
        this.directoryPath = directoryPath;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void setFileModelType(FileModelType fileModelType) {
        this.fileModelType = fileModelType;
    }
}
