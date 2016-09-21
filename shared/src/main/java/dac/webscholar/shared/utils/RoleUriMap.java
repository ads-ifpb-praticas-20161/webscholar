package dac.webscholar.shared.utils;

import java.util.Map;

/**
 * Created by marcusviniv on 20/09/2016.
 */
public class RoleUriMap {

    private String[] splitUri;
    private Map<String, Boolean> map;
    private String key;
    private Boolean value;

    public RoleUriMap(Map<String, Boolean> map, String uri){
        this.map = map;
        splitUri = uri.split("/");
    }

    public RoleUriMap findMatches(){
        for(int i = 0; i < splitUri.length; i++){
            if(map.containsKey(splitUri[i])){
                key = splitUri[i];
                value = map.get(key);
                System.out.println("lendo credenciais: key: " + key);
                System.out.println("lendo credenciais: value: " + value);
                return this;
            }
        }
        return this;
    }

    public boolean isAllowed(){
        if(key != null && value != null){
            return value;
        }
        else{
            return true;
        }
    }



}
