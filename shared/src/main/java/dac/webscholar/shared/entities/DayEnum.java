/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vmvini
 */
public enum DayEnum {
    
    SEGUNDA("SEGUNDA"), TERCA("TERCA"), QUARTA("QUARTA"), QUINTA("QUINTA"), SEXTA("SEXTA");


    DayEnum(String columnValue){
        this.columnValue = columnValue;
    }

    private String columnValue;

    public String getColumnValue(){
        return columnValue;
    }

    @Override
    public String toString(){
        return columnValue;
    }

    private static Map<String, DayEnum> getEnumMap(){
        Map<String, DayEnum> map = new HashMap<String, DayEnum>();
        map.put("SEGUNDA", DayEnum.SEGUNDA);
        map.put("TERCA", DayEnum.TERCA);
        map.put("QUARTA", DayEnum.QUARTA);
        map.put("QUINTA", DayEnum.QUINTA);
        map.put("SEXTA", DayEnum.SEXTA);
        return map;
    }

    public static DayEnum getDayEnum(String columnValue){
        System.out.println("PEGANDO ENUM DE " + columnValue);
        DayEnum d =  getEnumMap().get(columnValue);
        if(d == null){
            System.out.println("NAO EXISTE VALOR PARA " + columnValue);
        }
        return d;
    }


}
