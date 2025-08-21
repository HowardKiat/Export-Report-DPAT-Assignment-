/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package output;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Howard Tan How Kiat <Howard.Kiat at Asia Pacific University>
 */

public class FileOutputTarget implements OutputTarget {
    private final Path path;
    public FileOutputTarget(String filename) { this.path = Paths.get(filename); }

    @Override public void write(byte[] bytes) throws Exception {
        Files.createDirectories(path.toAbsolutePath().getParent());
        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
            fos.write(bytes);
        }
    }

    @Override public String description() { return "File(" + path.toAbsolutePath() + ")"; }
}