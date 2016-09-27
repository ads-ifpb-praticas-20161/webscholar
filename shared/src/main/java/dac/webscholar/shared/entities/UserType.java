/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import dac.webscholar.shared.utils.PropsLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author vmvini
 */
public enum UserType {

    ADMIN("admin.properties", "Administrador"),

    TEACHER("teacher.properties", "Professor"),

    PUBLIC("public.properties", "Anonimo");

    UserType(String propsFile, String columnValue){
        this.columnValue = columnValue;
        try {
            propsLoader = new PropsLoader();
            uriMap = propsLoader.getProperties(propsFile);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    protected PropsLoader propsLoader;
    protected Map<String, Boolean> uriMap;
    private String columnValue;

    public String getColumnValue(){
        return columnValue;
    }


    private static Map<String, UserType> getEnumMap(){
        Map<String, UserType> map = new HashMap<String, UserType>();
        map.put("Administrador", UserType.ADMIN);
        map.put("Professor", UserType.TEACHER);
        map.put("Anonimo", UserType.PUBLIC);
        return map;
    }

    public static UserType getTypeFromColumnValue(String c){
        return  getEnumMap().get(c);
    }

    public Map<String, Boolean> getUriMap(){
        return uriMap;
    }

}
