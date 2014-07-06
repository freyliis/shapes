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

    public Path openFile(String line) {
        Path file = null;
        try {
            String[] split = line.split(DELIMITER);
            if (split.length != 2) {
                System.out.println("Too many parameters");
            }
            final URL systemResource = ClassLoader.getSystemResource(split[1]);
            if (systemResource != null) {
                URI resource = systemResource.toURI();
                file = Paths.get(resource);

            } else {
                file = Paths.get(split[1]);
            }
            if (!Files.exists(file)) {
                System.out.println("File does not exist");
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(Shapes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }
}
