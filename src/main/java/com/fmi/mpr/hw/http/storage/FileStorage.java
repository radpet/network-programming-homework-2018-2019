package com.fmi.mpr.hw.http.storage;

import com.fmi.mpr.hw.http.common.Config;
import com.fmi.mpr.hw.http.common.IOUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileStorage {
    private final String basePath;

    public FileStorage() {
        this.basePath = Config.get(Config.FILE_BASE_PATH);
    }

    public void save(String filename, InputStream inputStream, int fileSize) throws IOException {
        try (OutputStream outStream = new FileOutputStream(Paths.get(basePath, filename).toFile())) {
            IOUtil.writeTo(inputStream, outStream, fileSize);
            outStream.flush();
        }
    }

    public Optional<Path> load(String path) {
        Path file = Paths.get(basePath, path);

        if (Files.exists(file) && !Files.isDirectory(file)) {
            return Optional.of(file);
        }
        return Optional.empty();
    }
}
