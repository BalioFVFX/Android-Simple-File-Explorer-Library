package com.example.simplefileexplorer;

import java.io.Serializable;

interface AdapterListener extends Serializable {
    void onDirectoryClick(String selectedAbsolutePath);
    void onFileClick(FileModel fileModel);
}
