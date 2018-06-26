/*
 * Copyright (C) 2018 stuartdd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package template;

import java.util.HashMap;
import java.util.Map;
import static junit.framework.Assert.*;
import org.junit.Test;

public class ExampleTemplate {

    private static final String EXPECTED = "Name value = Jo Bloggs \n"
            + "Java Vendor String = Oracle Corporation. Note this is not provided via the data object. \n"
            + ">>>Import value = Jo Bloggs<<<";

    @Test
    public void testImportFile() {
        assertEquals("", EXPECTED, testImport("src/test/resources/file_010.txt"));
    }

    @Test
    public void testClasspathMain() {
        assertEquals("", EXPECTED, testImport("classpath:/file_011.txt"));
    }

    @Test
    public void testClasspathClasspath() {
        assertEquals("", EXPECTED, testImport("classpath:/file_012.txt"));
    }
    
    @Test
    public void testClasspathDefered() {
        assertEquals("", EXPECTED, testImportDefered("classpath:/file_013.txt", "classpath:/file_010_1.txt"));
    }
    
    @Test
    public void testClasspathFileDefered() {
        assertEquals("", EXPECTED, testImportDefered("classpath:/file_013.txt", "src/test/resources/file_010_1.txt"));
    }
    
    @Test
    public void testFileFileDefered() {
        assertEquals("", EXPECTED, testImportDefered("src/test/resources/file_013.txt", "file_010_1.txt"));
    }

    @Test
    public void testFileClasspathDefered() {
        assertEquals("", EXPECTED, testImportDefered("src/test/resources/file_013.txt", "classpath:/file_010_1.txt"));
    }

    public String testImport(String mainFile) {
        /*
        Create a map with the data in it
         */
        Map<String, Object> data = new HashMap<>();
        Template template = new Template(mainFile);
        data.put("name", "Jo Bloggs");
        return template.parse(data);
    }

    public String testImportDefered(String mainFile, String subFile) {
        /*
        Create a map with the data in it
         */
        Map<String, Object> data = new HashMap<>();
        Template template = new Template(mainFile);
        data.put("name", "Jo Bloggs");
        data.put("subFile", subFile);
        return template.parse(data);
    }
}
