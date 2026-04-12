package assignment1;

import java.util.ArrayList;

public class Assignment1 {
    public Assignment1() {}

    // Problem 1
    // Timee Complexity O(n)
    public int findMin(int n, ArrayList<Integer> arr) {
        if (n == 1) {
            return arr.getFirst();
        }

        int m = findMin(n - 1, arr);
        if (m > arr.get(n - 1)) {
            return arr.get(n - 1);
        } else {
            return m;
        }
    }
    // Problem 2
    // Time Complexity O(n)
    public double findAverage(int n, ArrayList<Integer> arr) {
        if (n == 1) {
            return arr.getFirst();
        }

        double sum = arr.get(n - 1) + findAverage(n - 1, arr) * (n - 1);

        return sum/n;
    }

    // Problem 3
    // Time complexity O(n)
    public boolean isPrime(int n, int cur) {
        if (cur == n) {
            return true;
        }

        return isPrime(n, cur + 1) && n % cur != 0;

    }

    // Problem 4
    // Time Complexity O(n)
    public int findFactorial(int n) {
        if (n == 1) {
            return 1;
        }

        return findFactorial(n - 1) * n;
    }
    // Problem 5
    // Time complexity O(2^n)
    public int findFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return findFibonacci(n - 1) + findFibonacci(n - 2);
    }

    public int findExponent(int a, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        return a * findExponent(a, n - 1);
    }

    // Problem 7
    // Time complexity O(n!)
    public void printPermutation(String str, String ans) {
        if (str.isEmpty())  {
            System.out.println(ans);
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            printPermutation(ros, ans + ch);
        }

    }

    // Problem 8
    // Time complexity O(n)
    public boolean isDigit(String str) {
        if (str.isEmpty())  {
            return true;
        }

        char ch = str.charAt(str.length() - 1);

        return ch  >= '0' && ch <= '9' && isDigit(str.substring(0, str.length() - 1));
    }

    // Problem 9
    // Time complexity O(2^n)
    public int binomialCoef(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }

        return binomialCoef(n - 1, k - 1) + binomialCoef(n - 1, k);
    }

    // Problem 10
    // Time complexity O(log2(min(a, b)))
    public int gcd(int a, int b){
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
