package org.cis1200;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        System.out.println(fibonacciSequence(6));

    }

    public static int fibonacciSequence(int x) {
        if (x <= 1) {
            return 1;
        }
        return fibonacciSequence(x - 1) + fibonacciSequence(x - 2);
    }
}
