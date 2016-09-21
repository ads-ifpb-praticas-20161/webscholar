package dac.webscholar.shared.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by marcusviniv on 20/09/2016.
 */
public class PropsLoader {

    public Map<String, Boolean> getProperties(String file) throws IOException{

        Map<String, Boolean> map = new HashMap<>();
        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("META-INF/"+file);
        if (input == null) {
            throw new IOException("file not found");
        }

        prop.load(input);
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {

            String key = (String) e.nextElement();
            Boolean value = Boolean.parseBoolean( prop.getProperty(key) );
            map.put(key, value);
        }
        return map;

    }

}
