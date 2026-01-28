////Given an integer N, write a program to print your name N times.


import java.util.Scanner;

class RepN{
    public void PrintN(int n, String name){
        if(n==0) return;
        System.out.println(name);
        PrintN(n-1, name);
    }
}
public class Recursion1{
    public static void main(String[] args){
        RepN p = new RepN();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int N = sc.nextInt();
        System.out.println("Enter your name: ");
        String name = sc.next();
        p.PrintN(N, name);
        sc.close();
    }
}


