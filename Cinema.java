package cinema;

import java.util.Scanner;

public class Cinema {
    public static int ticks=0;
    public static int cost=0;
    public static char[][]mats;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seat = sc.nextInt();
        System.out.println();

        mats = new char[row][seat];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < seat; j++) {
                mats[i][j] = 'S';
            }

        }


        int totCost=0;
        if(row*seat<=60){
            totCost+=(row*seat)*10;
        }
        else{
            totCost+=(row/2)*seat*10;
            totCost+=(row-(row/2))*seat*8;
        }

        int totSeats=row*seat;
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int ans = sc.nextInt();
            System.out.println();

            switch (ans) {
                case 0:
                    return;
                case 1:
                    printseats();
                    break;
                case 2:
                    bookseats();
                    break;
                case 3:
                    showStats(totCost,totSeats);
                    break;
            }

        }

    }

    public static  void printseats(){
        System.out.println("Cinema:");

        for (int i = 0; i < mats.length+1; i++) {
            for (int j = 0; j < mats[0].length+1; j++) {
                if(i==0 && j==0){
                    System.out.print(" "+" ");
                }
                else if(j==0){
                    System.out.print(i+" ");
                }
                else if(i==0) {
                    System.out.print(j + " ");
                }else{
                    System.out.print(mats[i-1][j-1]+" ");
                }

            }
            System.out.println();

        }
        System.out.println();


    }
    public static void bookseats(){
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter a row number:");
        int y=sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int x=sc.nextInt();
        System.out.println();

        if(y>mats.length || y<1 || x<1 || x>mats[0].length){
            System.out.println("Wrong Input!");
            System.out.println();
            bookseats();
        }
        else if(mats[y-1][x-1]=='B'){
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            bookseats();

//            return;
        }
        else {
            System.out.print("Ticket price: $");
            if (mats.length * mats[0].length <= 60) {
                System.out.print(10);
                cost += 10;
                ticks++;
            } else {
                if (y <= mats.length / 2) {
                    System.out.print(10);
                    cost += 10;
                    ticks++;
                } else {
                    System.out.print(8);
                    cost += 8;
                    ticks++;
                }
            }

            mats[y - 1][x - 1] = 'B';
            System.out.println();
            System.out.println();
//            return;
        }
    }

    static void showStats(int totCost,int totSeats){
        System.out.println("Number of purchased tickets: "+ticks);
        System.out.print("Percentage: ");
        System.out.printf("%.2f",(float)ticks/totSeats*100);
        System.out.println("%");
        System.out.println("Current income: $"+cost);
        System.out.println("Total income: $"+totCost);
        System.out.println();
//        return;

    }
}
