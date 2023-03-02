/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_behaviours;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author venka
 */
public class JavaDates {
   
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static Date parseDate2(String date) {
        try {
            return new SimpleDateFormat("y/M/d h:m:s").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static void main(String[] args){
        Date d1 = parseDate("2022-03-25"); //parse a date
        Date d2 = new Date(); // current date and time
        Date d3 = new Date(System.currentTimeMillis() - 1*3600 * 1000); // 1 hour ago
        Date d4 = new Date(System.currentTimeMillis() - 2*3600 * 1000);// 2 hour ago
        Date d5 = new Date(System.currentTimeMillis() + 1*3600 * 1000); // 1 hour henceforth
        Date d6 = new Date(System.currentTimeMillis() + 2*3600 * 1000);// 2 hour henceforth
        Date twoDaysAgo = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 2);
        Date parsedDate = parseDate2("1999/04/21 10:10:10");
        
        
    }
    
}
