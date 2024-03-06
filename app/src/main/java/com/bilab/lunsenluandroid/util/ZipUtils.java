package com.bilab.lunsenluandroid.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    public static void unzip(File zipFile, File targetDirectory) throws Exception {
        try (FileInputStream fis = new FileInputStream(zipFile);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ZipInputStream zis = new ZipInputStream(bis)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File entryFile = new File(targetDirectory, entry.getName());

                // Create directory if it doesn't exist
                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    // Create parent directories if necessary
                    entryFile.getParentFile().mkdirs();

                    // Write file content
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(entryFile))) {
                        byte[] buffer = new byte[1024];
                        int count;
                        while ((count = zis.read(buffer)) != -1) {
                            bos.write(buffer, 0, count);
                        }
                    }
                }
            }
        }
    }
}
