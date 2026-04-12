package assignment1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Assignment1 a = new Assignment1();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(1);

        System.out.println(a.findAverage(4, list));
        System.out.println(a.isPrime(4, 2));
        System.out.println(a.findFactorial(5));
        a.printPermutation("ABC", "");
        System.out.println(a.isDigit("1224321321"));
        System.out.println(a.binomialCoef(7, 3));
        System.out.println(a.gcd(10, 7));
    }
}
