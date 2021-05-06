package com.company.Commands;

import com.company.Command;
import com.company.Main;
import com.company.Printer;

public class Remove_key  extends Command {
    @Override
    public void Execute(boolean is_thread) {
        if(args.size() == 1){
            if(Main.tickets.getTickets().remove(args.get(0)) != null){
                if(is_thread){
                    Printer.getInstance().WriteLine("удаление успешно");
                }
                else {
                    Main.writer.getResponces().add("удаление успешно");
                }
            }
            else{
                if(is_thread){
                    Printer.getInstance().WriteLine("удаление не удалось");
                }
                else {
                    Main.writer.getResponces().add("удаление не удалось");
                }
            }
        }
        else{
            if(is_thread){
                Printer.getInstance().WriteLine("неверное кол-во аргументов");
            }
            else {
                Main.writer.getResponces().add("неверное кол-во аргументов");
            }
        }
    }
}
