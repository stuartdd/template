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
import org.junit.Test;

public class ExampleOne {

    @Test
    public void testOne() {
        Map<String, Object> data = new HashMap<>();
        int numberOfLines = 5;

        Template template = new Template("E:/Development/Template/src/test/resources/file_005.txt");
        System.out.println(template.getTemplateText());
        data.put("name", "Jo Bloggs");
        data.put("date", new Date((long) 1234567894));
        data.put("num", 12345);
        data.put("numberOfLines", numberOfLines);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) { // Include n instances of a template. 
            Map<String, Object> m = new HashMap<>(); // Create 1 map per template instance 
            m.put("num", i + 1); // add some data. Note this num overrides the data num inside repeat
            m.put("lineText", "Some text from line " + (i + 1));
            list.add(m);
        }
        data.put("file_005_1.txt", list); // Add the Maps to the list }
        
        String s = template.parse(data);
        System.out.println("S:["+s+"]");
    }
}
