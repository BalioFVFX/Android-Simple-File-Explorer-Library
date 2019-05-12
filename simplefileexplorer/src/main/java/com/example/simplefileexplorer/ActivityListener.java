package com.example.simplefileexplorer;

import java.io.Serializable;

interface ActivityListener extends Serializable {
    void onDirectoryChanged(String absolutePath);
    void onFileSelect(String absolutePath);
}
