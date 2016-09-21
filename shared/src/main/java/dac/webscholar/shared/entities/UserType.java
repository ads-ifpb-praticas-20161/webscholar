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

    ADMIN("admin.properties"),

    TEACHER("teacher.properties"),

    PUBLIC("public.properties");

    UserType(String propsFile){
        try {
            propsLoader = new PropsLoader();
            uriMap = propsLoader.getProperties(propsFile);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    protected PropsLoader propsLoader;
    protected Map<String, Boolean> uriMap;


    public Map<String, Boolean> getUriMap(){
        return uriMap;
    }

}
