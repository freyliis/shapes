/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author freylis
 */
public class FileServiceTest {

    @Test
    public void shouldReturnTrueIfFileWithMarksIsProperRead() {
        FileService fileService = new FileService();
        String fileName = "E:\\New folder\\test.txt";
        String line = "file \"" + fileName + "\"";
        String parsedFileName = fileService.getFileName(line);
        assertThat(parsedFileName, CoreMatchers.is(fileName));
    }

    @Test
    public void shouldReturnTrueIfFileWithSpacesIsProperRead() {
        FileService fileService = new FileService();
        String fileName = "test.txt";
        String line = "file " + fileName;
        String parsedFileName = fileService.getFileName(line);
        assertThat(parsedFileName, CoreMatchers.is(fileName));
    }

}
