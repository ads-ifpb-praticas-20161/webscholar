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

    TEACHER("teacher.properties");

    UserType(String propsFile){
        try {
            propsLoader = new PropsLoader();
            blockedUrls = propsLoader.getProperties(propsFile);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    protected PropsLoader propsLoader;
    protected List<String> blockedUrls;

    public boolean isBlocked(String requestedUrl){
        return blockedUrls.contains(requestedUrl);
    }

    
}
