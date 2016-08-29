/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 *
 * @author vmvini
 */
public class GenericUtils {
    
    public static Class getSuperClassGenericType(Class clazz){
        return getSuperClassGenericType(clazz, 0);
    }
    
    
    public static Class getSuperClassGenericType(Class clazz, int index){
        Type genericType = clazz.getGenericSuperclass();
        if (!(genericType instanceof ParameterizedType)){
            return Object.class;
        }
        Type[] params = ((ParameterizedType)genericType)
            .getActualTypeArguments();
        if (index >= params.length || index < 0){
            throw new IllegalArgumentException
            ("The index is invalid:"+index);
        }
        return (Class)params[index];
    }
}
