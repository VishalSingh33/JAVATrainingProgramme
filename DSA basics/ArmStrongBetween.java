package com.zyapaar.analytics.properties;

import java.util.*;

public class ArmStrongBetween {
    public static void main(String[] args) {

        int num, a;
        for (num = 1; num <= 10000; num++) {
            System.out.println(num);

            int b = 0;
            int numb1 = 0;
            int temp = num;
            while (num != 0) {
                num = num/10;
                b++;
            }
            num = temp;
            while (num > 0) {
                a = num % 10;
                numb1 = (int) (numb1 + Math.pow(a,b));
                num = num / 10;
            }
            num = temp;
            if (num == numb1) {

                System.out.println("this is armStrong numb");
            } else {

                System.out.println("this is not an armStrong numb");
            }
        }


        }
    }