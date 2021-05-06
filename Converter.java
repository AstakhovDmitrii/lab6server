package com.company;


import com.company.Models.Transform_date;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.transform.RegistryMatcher;

import java.io.*;
import java.time.ZonedDateTime;

public class Converter {// по сути у этого класса всегда один обьект. И сам класс упрвляет им
    private final Persister Persister;// конвертер
    private static Converter instance;

    private Converter(){

        RegistryMatcher matchers = new RegistryMatcher();
        matchers.bind(ZonedDateTime.class, Transform_date.class);// создаем способ записи ZonedDateTime
        Strategy strategy = new AnnotationStrategy();
        Persister = new Persister( strategy , matchers );
    }

    public static Converter getInstance() {
        if(instance == null){
            instance = new Converter();
        }
        return instance;
    }
    public <T> String Write(T obj) {
        try {
            Writer writer = new StringWriter();
            Persister.write(obj, writer);
            return writer.toString();
        }
        catch (Exception e){
            return null;
        }
    }
    public <T> T Read(Class<? extends T> T, String str) {
        try {
            return Persister.read(T, str);
        }
        catch (Exception ignored){
            return null;
        }
    }
    public <T> void write_to_file(T obj, String path){
        try {
            Persister.write(obj, new File(path));
        }
        catch (Exception ignored){

        }
    }
    public <T> T Read_file(Class<? extends T> T, String path) {
        try {
            return Persister.read(T, new File(path));
        }
        catch (Exception ignored){
            return null;
        }
    }

}
