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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static junit.framework.Assert.*;
import org.junit.Test;

public class ExampleRepeat {

    private static final String EXPECTED = "Name value = Jo Bloggs \n"
            + "Date = Thu Jan 15 07:56:07 GMT 1970 \n"
            + "Num = 12345 \n"
            + "Java Vendor String = Oracle Corporation. Note \n"
            + "this is not provided via the data object. \n"
            + "--- Now the multiple lines \n"
            + " Line 1 of 3 : Some text from line 1. Note there is a new line at the end of this file\n"
            + " Line 2 of 3 : Some text from line 2. Note there is a new line at the end of this file\n"
            + " Line 3 of 3 : Some text from line 3. Note there is a new line at the end of this file\n"
            + "--- End the multiple lines \n"
            + "Num = 12345";

    @Test
    public void testOne_File() {
        assertEquals("Result", EXPECTED, testRepeatWithTwoFiles("src/test/resources/file_005.txt", "file_005_1.txt"));
    }

    @Test
    public void testTwo_Classpath() {
        /*
        file_006.txt (loaded via the classpath) has a repeat file name classpath:/file_005_1_CP.txt so the list 
        must be in a property called 'classpath:/file_005_1_CP.txt'.
         */
        assertEquals("Result", EXPECTED, testRepeatWithTwoFiles("classpath:/file_006.txt", "classpath:/file_005_1_CP.txt"));
    }

    @Test
    public void testTwo_Mixed1() {
        /*
        file_006.txt (loaded as a file) has a repeat file name classpath:/file_005_1_CP.txt so the list 
        must be in a property called 'classpath:/file_005_1_CP.txt'.
         */
        assertEquals("Result", EXPECTED, testRepeatWithTwoFiles("src/test/resources/file_006.txt", "classpath:/file_005_1_CP.txt"));
    }

    @Test
    public void testTwo_Mixed2() {
        /*
        file_007.txt (loaded via the classpath) has a repeat file name src/test/resources/file_005_1.txt so the list 
        must be in a property called 'src/test/resources/file_005_1.txt'.
         */
        assertEquals("Result", EXPECTED, testRepeatWithTwoFiles("classpath:/file_007.txt", "src/test/resources/file_005_1.txt"));
    }

    @Test
    public void testTwo_defered() {
        /*
        file_008.txt (loaded via the classpath) has a repeat that uses a property (deferedFileName) to hold the file name for the repeat# clause.        
         */
        assertEquals("Result", EXPECTED, testRepeatWithTwoFiles("classpath:/file_008.txt", "src/test/resources/file_005_1.txt"));
    }

    public String testRepeatWithTwoFiles(String mainFile, String repeatFile) {
        /*
        Create a map with the data in it
         */
        Map<String, Object> data = new HashMap<>();
        int numberOfLines = 3;
        /*
        Define a template file. Note file_005_1.txt should be in the same directory
         */
        Template template = new Template(mainFile);
        /*
        Define regular substitutiions
        deferedFileName is only required for testTwo_defered(). 
         */
        data.put("deferedFileName", repeatFile);
        data.put("name", "Jo Bloggs");
        data.put("date", new Date((long) 1234567894));
        data.put("num", 12345);
        data.put("numberOfLines", numberOfLines);

        /*
        Create a List or Maps. 1 Map per embedded file.
        Make each row different for the test.
         */
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) { // Include n instances of a template. 
            Map<String, Object> m = new HashMap<>(); // Create 1 map per template instance 
            m.put("num", i + 1); // add some data. Note this num overrides the data num inside repeat
            m.put("lineText", "Some text from line " + (i + 1));
            list.add(m);
        }
        /*
        Add the list to the data. Note the file name is the property name.
         */
        data.put(repeatFile, list); // Add the Maps to the list }            
        /*
        Create the resultant string.
         */
        return template.parse(data);
    }
}
