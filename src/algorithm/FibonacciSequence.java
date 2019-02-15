package algorithm;

//    Description
//    Find the Nth number in Fibonacci sequence.
//
//    A Fibonacci sequence is defined as follow:
//
//    The first two numbers are 0 and 1.
//    The i th number is the sum of i-1 th number and i-2 th number.
//    The first ten numbers in Fibonacci sequence is:
//
//    0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...

import java.math.BigDecimal;
import java.util.Scanner;

public class FibonacciSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input Nth:");
        int Nth = scanner.nextInt()-1;
        System.out.println("result:"+getFibonacciNumOfNthByNotRecursive(Nth));
    }

    private static BigDecimal getFibonacciNumOfNthByRecursive(int n){
        if (n==0)
            return BigDecimal.ZERO;
        if (n==1)
            return BigDecimal.ONE;
        return getFibonacciNumOfNthByRecursive(n-1).add(getFibonacciNumOfNthByRecursive(n-2));
    }

    public static BigDecimal getFibonacciNumOfNthByNotRecursive(int n){
        if (n==0)
            return BigDecimal.ZERO;
        if (n==1)
            return BigDecimal.ONE;
        int i = 2;
        BigDecimal r = null;
        BigDecimal r_1 = BigDecimal.ONE;
        BigDecimal r_2 = BigDecimal.ZERO;
        while (i <= n){
            r = r_1.add(r_2);
            r_2 = r_1;
            r_1 = r;
            i++;
        }
        return r;
    }
}

