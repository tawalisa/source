package com.lijia.filedb.util;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;

public class ObjectUtil {
    public ObjectUtil() {
        super();
    }

    public static List getObjFields(Object obj) {
        return getObjFields(obj, null);
    }


    public static List getObjFields(Object obj, Set rsFields) {
        List lRet = new ArrayList();
        Field[] fieldlist;
        try {
            fieldlist = obj.getClass().getDeclaredFields();
            if (fieldlist == null) {
                return lRet;
            }
            for (int i = 0; i < fieldlist.length; i++) {
                if (rsFields != null &&
                    !rsFields.contains(fieldlist[i].getName().toLowerCase())) {
                    continue;
                }
                lRet.add(fieldlist[i]);

            }
        } catch (Exception ex) {
        }
        return lRet;
    }

    public static Method[] gettters(Object obj){
        Method[] methods = obj.getClass().getMethods();
        String methodname = "";
        for(int i = 0;i< methods.length;i++){
            methodname = methods[i].getName();
            if (methodname.indexOf("get")!=0)
                methods[i] = null;
        }
        return methods;

    }
    public static Method[] settters(Object obj){
        Method[] methods = obj.getClass().getMethods();
        String methodname = "";
        for(int i = 0;i< methods.length;i++){
            methodname = methods[i].getName();
            if (methodname.indexOf("set")!=0)
                methods[i] = null;
        }
        return methods;
    }


    public static Map getValidField(Object obj,Set tablefields){
        Map fieldset = new HashMap();
        Field[] fields = null;
        try {
            fields = obj.getClass().getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                if(!field.getType().isPrimitive()){
                    if(field.getType().isSynthetic())
                        continue;
                    if(field.get(obj)==null)
                        continue;
                }
                if(tablefields==null || tablefields.contains(field.getName().toLowerCase()))
                    fieldset.put(field.getName(),field.getType().getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fieldset;
    }


    private static Field getField(Object obj,String fieldname){
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.getName().equalsIgnoreCase(fieldname))
                return field;
        }
        return null;
    }

    public static Object getFieldValue(Object obj,String fieldname){
        Field t = getField(obj,fieldname);
        try {
            t.setAccessible(true);
            return t.get(obj);
        } catch (Exception ex) {
            return null;
        }

    }
	public static void setFieldValue(Object obj, String name, Object val) throws IllegalArgumentException, IllegalAccessException {
		Field t = getField(obj,name);
        t.setAccessible(true);
        t.set(obj, val);
	}
}
