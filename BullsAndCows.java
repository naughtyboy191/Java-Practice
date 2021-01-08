package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String inp=sc.nextLine();
        int codeLength;
        try {

             codeLength = Integer.parseInt(inp);
        }catch (Exception e){
            System.out.println("Error: \""+inp+"\" isn't a valid number.");
            return;
        }

        if(codeLength>36||codeLength<=0){
            System.out.println("Error: can't generate a secret number with a length of "+codeLength+" because there aren't enough unique digits.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int codeChars=sc.nextInt();

        if(codeChars>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");


            return;
        }
        if(codeChars<codeLength){
            System.out.println("Error: it's not possible to generate a code with a length of "+codeLength+" with "+codeChars+" unique symbols.");
            return;
        }
        String code=gencode(codeLength,codeChars);

        while(!uniqueCharacters(code)){
            code=gencode(codeLength,codeChars);
        }
//        System.out.println(code);
        String charSet;
        if(codeChars<11){
            charSet="(0-"+(codeChars-1);
        }
        else if(codeChars==11){
            charSet="(0-9, a";
        }
        else{

            charSet="(0-9, a-" +(char)((int)'a'+codeChars-11);
        }

        charSet+=").";

        System.out.println("The secret is prepared: "+ "*".repeat(Math.max(0, codeLength)) +" "+charSet);

        int ans=-1,turn=1;
        System.out.println("Okay, let's start a game!");

        while(ans!=0){
            System.out.println("Turn "+turn+":");
            ans=checkGrade(code);
            turn++;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
    public static String gencode(int codeLength,int codeChars){
        StringBuilder code= new StringBuilder();
        Random random= new Random();
        for (int i = 0; i <codeLength; i++) {
            int n1 = random.nextInt(codeChars);
            n1 = n1 < 10 ? n1 + 48 : n1 + 87;
            code.append((char) n1);
        }

        return code.toString();
    }
    static boolean uniqueCharacters(String str)
    {
        int MAX_CHAR = 256;
        // If length is greater than 256,
        // some characters must have been repeated
        if (str.length() > MAX_CHAR)
            return false;

        boolean[] chars = new boolean[MAX_CHAR];
        Arrays.fill(chars, false);

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);

            /* If the value is already true, string
               has duplicate characters, return false */
            if (chars[index])
                return false;

            chars[index] = true;
        }

        /* No duplicates encountered, return true */
        return true;
    }
    public static int checkGrade(String code) {
        Scanner sc = new Scanner(System.in);

        String guess = sc.next();
        if(code.equals(guess)){
            System.out.println("Grade: "+(code.length()==1?code.length()+" bull":code.length()+" bulls"));
            return 0;}
        int bull = 0, cow = 0;
        for (int i = 0; i < code.length(); i++) {
            int i1 = code.indexOf(guess.charAt(i));
            if(i1 !=-1){
            if(i1 ==i){
                bull++;
            }
            else{
                cow++;
            }
        }
        }
        String ans="Grade: ";
        if(bull!=0){
            if(bull==1){
                ans+="1 bull";
            }
            else{
                ans+=bull+" bulls";
            }
            if(cow!=0){
                ans+=" and ";
                if(cow==1){
                    ans+="1 cow";
                }
                else{
                    ans+=cow+" cows";
                }
            }


        }else{
            if(cow!=0){
                if(bull==1){
                    ans+="1 cow";
                }
                else{
                    ans+=cow+" cows";
                }
            }
            else{
                ans="None";
            }
        }

        System.out.println(ans);
        return 1;



}


}

