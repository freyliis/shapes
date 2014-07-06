/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import freylis.shapes.shapes.Shapes;
import static freylis.shapes.shapes.Shapes.DELIMITER;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author freylis
 */
public class FileService {

    private static final String MARKS = "\"";

    public Path openFile(String line) {
        Path file = null;
        try {
            String fileName = getFileName(line);
            final URL systemResource = ClassLoader.getSystemResource(fileName);
            if (systemResource != null) {
                URI resource = systemResource.toURI();
                file = Paths.get(resource);

            } else {
                file = Paths.get(fileName);
            }
            if (!Files.exists(file)) {
                System.out.println("File does not exist");
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Shapes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    String getFileName(String line) {
        String[] split;
        if (line.contains(MARKS)) {
            split = line.split(MARKS);
        } else {
            split = line.split(DELIMITER);
        }
        if (split.length != 2) {
            throw new RuntimeException("Too many parameters for file");
        }
        return split[1];
    }
}
