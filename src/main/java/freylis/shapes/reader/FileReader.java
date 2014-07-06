/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.reader;

import static freylis.shapes.shapes.Shapes.DELIMITER;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author freylis
 */
public class FileReader implements Reader {

    private static final String MARKS = "\"";
    private Path path;
    private BufferedReader reader;
    private String readLine;

    public FileReader() {
    }

    public void init(String fileName) {
        path = getFile(fileName);
        reader = getReader();
    }

    private BufferedReader getReader() {
        try {
            Charset charset = Charset.forName("US-ASCII");
            return Files.newBufferedReader(path, charset);
        } catch (IOException ex) {
            LOGGER.debug(ex);
        }
        return null;
    }

    private Path getFile(String line) {
        Path file = null;
        try {
            String fileName = getFileName(line);
            URL systemResource = FileReader.class.getResource(fileName);
            if (systemResource == null) {
                systemResource = FileReader.class.getResource("/" + fileName);
                if (systemResource != null) {
                    URI resource = systemResource.toURI();
                    file = Paths.get(resource);

                } else {
                    file = Paths.get(fileName);
                }
            }
            if (!Files.exists(file)) {
                throw new RuntimeException("File " + file + " does not exist");
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

    @Override
    public String nextLine() {
        return readLine;
    }

    @Override
    public boolean hasNextLine() {
        if (reader != null) {
            try {
                readLine = reader.readLine();
                return readLine != null;
            } catch (IOException ex) {
                LOGGER.debug(ex);
            }
        }
        return false;
    }

}
