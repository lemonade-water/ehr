package com.neusoft.ehr.utils;

import com.neusoft.ehr.service.ImpServiceFac;

public  class FactoryUntil {



    //反射回ImpServiceFac接口对象
    public static <T> T getClass(Class<T> clazz){
        //获得class的类名
        String classname = clazz.getSimpleName();
        //根据类名获得类的对象
        String path = ClassPropertiesUtils.getProperty(classname);

        try {
            return (T) Class.forName(path).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }






    //反射回ImpDaoFac接口对象




    //利用反射获得对象
    public static Object getObject(String string){
        String path = ClassPropertiesUtils.getProperty(string);
        Object object = null;
        try {
            Class<?> clazz = Class.forName(path);
            object = clazz.newInstance();
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  object;
    }
    public static String getClassName(String string){
        String path = ClassPropertiesUtils.getProperty(string);
        String className = path.substring(path.lastIndexOf(".")+1);
        return  className;
    }
}
