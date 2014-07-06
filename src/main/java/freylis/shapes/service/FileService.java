/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import static freylis.shapes.shapes.Shapes.DELIMITER;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            URL systemResource = FileService.class.getResource(fileName);
            if (systemResource == null) {
                systemResource = FileService.class.getResource("./" + fileName);
                if (systemResource != null) {
                    URI resource = systemResource.toURI();
                    file = Paths.get(resource);

                } else {
                    file = Paths.get(fileName);
                }
            }
            if (!Files.exists(file)) {
                throw new RuntimeException("File " + file.toAbsolutePath() + " does not exist");
            }
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
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
