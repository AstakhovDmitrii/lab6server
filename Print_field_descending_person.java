package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Models.Ticket;
import com.company.Printer;

import java.util.Arrays;
import java.util.Comparator;

public class Print_field_descending_person  extends Command {
    @Override
    public void Execute(boolean is_thread) {
        try {
            Object[] arrays = Main.tickets.getTickets().values().toArray();
            Arrays.sort(arrays, Comparator.comparing(o -> ((Ticket)o)));// сортируем массив по убыванию
            for (Object ticket : arrays) {// выводим элементы
                if(is_thread){
                    Printer.getInstance().WriteLine(ticket.toString());
                }
                else {
                    Main.writer.getResponces().add(ticket.toString());
                }
            }
        }
        catch (Exception e){
            Main.writer.getResponces().add(e.getMessage());
        }
    }
}
