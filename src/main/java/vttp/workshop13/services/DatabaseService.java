package vttp.workshop13.services;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private File dataDir = new File("pathname");

    // getters and setters
    public File getDataDir() {
        return dataDir;
    }
    public void setDataDir(File f) {
        this.dataDir = f;
    }

    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }
}
