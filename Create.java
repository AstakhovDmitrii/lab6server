package com.company;


import com.company.Models.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Create {
    public static ArrayList<Pole> get_fields(Object object) {
        ArrayList<Pole> poles = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if(!field.getName().equals("creationDate") && !field.getName().equals("id")) {
                    if (field.getType().isPrimitive() || field.getType().isEnum() || field.getType() == Integer.class ||
                            field.getType() == String.class || field.getType() == Float.class || field.getType() == Long.class ||
                            field.getType() == Double.class) {
                        poles.add(new Pole(field, object));
                    } else {
                        poles.addAll(get_fields(field.get(object)));
                    }
                }
            }
        }
        catch (Exception ignored){

        }
        return poles;
    }

    public static Ticket Set_Fields() {
        Ticket product = new Ticket();//создаем перемнные
        product.setCoordinates(new Coordinates());
        product.setPerson(new Person());
        try {
            ArrayList<Pole> poles = get_fields(product);
            for (Pole pole : poles) {
                pole.getField().setAccessible(true);
                System.out.println("введите поле " + pole.getField().getName());
                while (true) {//вводим поле. Если оно одно из типов, приводим его к этому типу
                    try {
                        if (pole.getField().getType() == int.class || pole.getField().getType() == Integer.class)
                            pole.getField().set(pole.getMain(), Integer.parseInt(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == double.class || pole.getField().getType() == Double.class)
                            pole.getField().set(pole.getMain(), Double.parseDouble(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == long.class)
                            pole.getField().set(pole.getMain(), Long.parseLong(Printer.getInstance().ReadLine()));
                        else if (pole.getField().getType() == TicketType.class) {
                            for (TicketType TicketType : TicketType.values())
                                System.out.print("\t\t\t\t" + TicketType);
                            System.out.println();
                            pole.getField().set(pole.getMain(), TicketType.valueOf(Printer.getInstance().ReadLine()));
                        }
                        else if(pole.getField().getType() == Float.class || pole.getField().getType() == float.class)
                            pole.getField().set(pole.getMain(), Float.parseFloat(Printer.getInstance().ReadLine()));
                        else
                            pole.getField().set(pole.getMain(), pole.getField().getType().getDeclaredMethod("valueOf", Object.class)
                                    .invoke(pole.getMain(), Printer.getInstance().ReadLine()));
                        break;
                    } catch (IllegalArgumentException a) {
                        Printer.getInstance().WriteLine("введите поле " + pole.getField().getName() + " еще раз");
                    }
                }
            }
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            Printer.getInstance().WriteLine("что то поломалось " + e.getMessage());
        }
        return product;
    }
}
