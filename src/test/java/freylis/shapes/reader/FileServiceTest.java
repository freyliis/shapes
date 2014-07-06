/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freylis.shapes.reader;

import freylis.shapes.reader.FileReader;
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
        String fileName = "E:\\New folder\\test.txt";
        String line = "file \"" + fileName + "\"";
        FileReader fileService = new FileReader();
        String parsedFileName = fileService.getFileName(line);
        assertThat(parsedFileName, CoreMatchers.is(fileName));
    }

    @Test
    public void shouldReturnTrueIfFileWithSpacesIsProperRead() {
        String fileName = "test.txt";
        String line = "file " + fileName;
        FileReader fileService = new FileReader();
        String parsedFileName = fileService.getFileName(line);
        assertThat(parsedFileName, CoreMatchers.is(fileName));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExcpetionIfFileWithMarksIsNotProperRead() {
        String fileName = "E:\\New folder\\test.txt";
        String line = "file " + fileName;
        FileReader fileService = new FileReader();
        String parsedFileName = fileService.getFileName(line);
        assertThat(parsedFileName, CoreMatchers.is(fileName));
    }

}
