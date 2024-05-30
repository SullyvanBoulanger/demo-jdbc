package fr.diginamic.props;

import java.util.Iterator;
import java.util.ResourceBundle;

public class TestConfigurationProps {
    public static void main(String[] args) {
        ResourceBundle config = ResourceBundle.getBundle("config");

        System.out.println("What is heaven ?\n" + config.getString("heaven"));

        Iterator<String> keys = config.getKeys().asIterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key + " : " + config.getString(key));
        };
    }
}
