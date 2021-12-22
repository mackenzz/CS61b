package com.company;

import java.util.*;

public class SmallFunctions {
    public static void drawTrianngle(int n) {
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j <= i; j ++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void windowPosSum(int[] a, int b) {
        for (int i = 0; i < a.length; i ++) {
            if (i + b + 1 < a.length) {
                for (int j = i + 1; j < i + b + 1; j ++) {
                    a[i] += a[j];
                }
            } else {
                for (int j = i + 1; j < a.length; j ++) {
                    a[i] += a[j];
                }
            }
        }
    }



}





