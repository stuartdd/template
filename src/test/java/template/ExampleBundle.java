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

public class ExampleBundle {

    private static final String EXPECTED = "Load the bundle. \n"
            + "Name value = Jo Bloggs. Note name is NOT from the bundle!\n"
            + "Date = 1st July \n"
            + "Num = 123456 \n"
            + "Java Vendor String = Oracle Corporation. Note this is not provided via the data object. \n"
            + ">>>Import value = Jo Bloggs<<<";

    @Test
    public void testImportBundleNotFound() {
        assertTrue("", testImportBundle("classpath:/file_021.txt").contains("+++ERROR: Bundle:bundle.properties:notFound +++"));
    }

//    @Test
//    public void testImportBundle() {
//        assertEquals("", EXPECTED, testImportBundle("src/test/resources/file_021.txt"));
//    }

    public String testImportBundle(String mainFile) {
        /*
        Create a map with the data in it
         */
        Map<String, Object> data = new HashMap<>();
        Template template = new Template(mainFile);
        data.put("name", "Jo Bloggs");
        String s = template.parse(data);
        System.out.println(s);
        return s;
    }

}
