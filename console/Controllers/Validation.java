package Controllers;

import models.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    private static final Scanner sc = new Scanner(System.in);
    private static final int TRY = 5; // Maximum wrong tries.

    //A method to validate integer.
    public int returnInt(String label){
        int returnInt = 0;
        for(int i=0; i<TRY; i++) {
            System.out.println("Enter "+label+ " : ");
            try {
                returnInt = Integer.parseInt(sc.nextLine());
                if (returnInt>=0)
                    return returnInt;
                System.out.println("Negative integer is not valid.");
            }catch(NumberFormatException e){
                System.out.println("Please enter a valid integer.");
            }
        }
        System.out.println("\nSystem going down due to multiple continuous wrong inputs.");
        System.exit(1);
        return returnInt;
    }

    public int validateDate(String date){
        try{
            int day  = Integer.parseInt(date.substring(0,2));
            int month= Integer.parseInt(date.substring(2,4));
            int year = Integer.parseInt(date.substring(4,8));
            Date check = new Date(day,month,year);
        }catch (Exception e){
            return 100;
        }
        return Integer.parseInt(date);
    }

    // method to validate strings.
    public String returnString(String label){
        String text= "End";
        for(int i=0; i<TRY; i++) {
            System.out.println("Enter "+label+ " : ");
            try {
                text = checkChar(sc.nextLine());
                if(!(text.length()==0))
                    return text;
                System.out.println("Don't leave blank");
            }catch(InputMismatchException e){
                String mismatch = e.getMessage();
                System.out.println("Character '"+mismatch+"' was found. Type strings with valid characters");
            }
        }
        System.out.println("\nSystem going down due to multiple continuous wrong inputs.");
        System.exit(1);
        return text;
    }

    // checks for integers and other characters other than alphabet in a string.
    private static String checkChar(String text){
        boolean space = true;
        String temp = "";
//      RegEx Reference : https://www.regextutorial.org/regex-for-numbers-and-ranges.php
        for(int i =0; i!=text.length();i++){
            String letter = text.substring(i,i+1);
            if (letter.equals(" ")){
                if(!space){
                    temp = temp+" ";
                }
                space=true;
            }
            else if(letter.matches("[a-z]|[A-Z]")){
                temp = temp + letter;
                space = false;
            }
            else {
                throw new InputMismatchException(letter);
            }
        }
        return temp;
    }

    //A method used for spacing ints. Ex : "1" will be returned as " 1"
    public static String zeroChecker(int display){
        if(0<=display && display<10){
            return "  "+display+" ";
        }
        return " "+display+" ";
    }

    //A method to leave spaces evenly between the table.
    //Only used when displaying team names in the points table
    public static String clubNameWithSpace(String clubName,int max){
        int difference = max-clubName.length();
        if(difference!=0){
            for(int x=0;x<difference;x++){
                clubName = clubName+" ";
            }
        }
        return " "+ clubName+" ";
    }
}
