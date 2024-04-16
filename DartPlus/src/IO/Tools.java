package IO;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Items.*;
import Utilities.*;

public class Tools {
    private static Scanner input = new Scanner(System.in);

    public static void exitProgram(){
        input.close();
        System.exit(0);
    }
    public static String getString(String message, Scanner input){
        System.out.println(message);
        String userInput = input.nextLine();
        return userInput;
    }
    public static char getChar(String message){
        System.out.println(message);
        char userInput = input.next().charAt(0);
        return userInput;
    }
    public static int getInt(String message){
        System.out.println(message);
        int userInput = input.nextInt();
        input.nextLine();
        return userInput;
    }
    public static double getDouble(String message){
        System.out.println(message);
        double userInput = input.nextDouble();
        input.nextLine();
        return userInput;
    }
    public static long calcDays(Rentable item){
        if(ChronoUnit.DAYS.between(item.getRentDate(), item.getReturnDate()) < 0) {
            throw new EarlyDateException("Invalid operation. Upon returning an item, the number of days rented must be positive.");
        } else {
            return ChronoUnit.DAYS.between(item.getRentDate(), item.getReturnDate());
        }
    }

    // The UUID suggested for creating unique ID's creates a long string of numbers, letters and dashes.
    // Such as: "123e4567-e89b-12d3-a456-556642440000"
    // We found this difficult to read, and hard for the user to input by themselves.
    // Because of this we created a generator which creates a 6 character long id, which is unique.
    //
    // https://stackoverflow.com/questions/9543715/generating-human-readable-usable-short-but-unique-ids

    private static final ArrayList<String> IDCheck = new ArrayList<>();

    public static String randomizeID(){
        boolean loop = true;
        while(loop) {
            String ID = GetBase62(6);
            if (!IDCheck.contains(ID)) IDCheck.add(ID); loop = false;
        }
        return IDCheck.get(IDCheck.size() -1);
    }

    private static final char[] _base62chars ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final Random _random = new Random();

    public static String GetBase62(int length)
    {
        var sb = new StringBuilder(length);
        for (int i=0; i<length; i++)
            sb.append(_base62chars[_random.nextInt(62)]);
        return sb.toString();
    }
    public static boolean validateChar(char x, String correct){
        Scanner valChar = new Scanner(System.in);
        boolean programRunning = true;
        while(programRunning) {
            for (int i = 0; i < correct.length(); i++) {
                if (x == correct.charAt(i)) {
                    programRunning = false;
                    return true;
                }
            }
            if(programRunning) {
                System.out.println("Invalid input, try again.");
                x = valChar.next().charAt(0);
            }
        }
        return false;
    }
//&begin Security
    public static String getPassword(){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println("Password generated " + generatedString);
        return generatedString;
    }
    public static boolean verifyPassword(String correctPassword){
        boolean correct = false;
        System.out.println("Enter password: ");
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();
        if (password.equals(correctPassword)) correct = true;
        return correct;
    }
//&end Security
}
