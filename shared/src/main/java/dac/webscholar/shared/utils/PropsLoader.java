package dac.webscholar.shared.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by marcusviniv on 20/09/2016.
 */
public class PropsLoader {

    public List<String> getProperties(String file) throws IOException{

        List<String> keys = new ArrayList<>();
        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("META-INF/"+file);
        if (input == null) {
            throw new IOException("file not found");
        }

        prop.load(input);
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            keys.add(key);
            //String value = prop.getProperty(key);
        }
        return keys;

    }

}
