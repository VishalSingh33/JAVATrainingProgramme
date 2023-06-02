import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public String transform(String s) {
        // code here
        char a[] = s.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (a[i] == " " && ch[i] >= 'a' && ch[i] <= 'z'
            
                    // a[i + 1] == 'a' || a[i + 1] == 'b' || a[i + 1] == 'c' || a[i + 1] == 'd'
                    // || a[i + 1] == 'e' || a[i + 1] == 'f' || a[i + 1] == 'g' || a[i + 1] == 'h' || a[i + 1] == 'i'
                    // || a[i + 1] == 'j' || a[i + 1] == 'k' || a[i + 1] == 'l' || a[i + 1] == 'm' || a[i + 1] == 'n'
                    // || a[i + 1] == 'p' || a[i + 1] == 'q' || a[i + 1] == 'r' || a[i + 1] == 's' || a[i + 1] == 't'
                    // || a[i + 1] == 'u' || a[i + 1] == 'v' || a[i + 1] == 'w' || a[i + 1] == 'x' || a[i + 1] == 'y'
                    // || a[i + 1] == 'o' || a[i + 1] == 'z'
                    ) {
                a[i+1] = (char)(ch[i] - 'a' + 'A');
                    }
                else {
                    a[0] = (char)(ch[i] - 'a' + 'A');

                }
            }
        }
}

// char[] c = s.toCharArray();
// int i,j;

// // char[] c = s.toCharArray();
// // int i = 0;
// // int j = c.length - 1;

// // for (i = 0; i < c.length - 1; i++) {
// // if (c[i] == 'j' || c[i] == 'e' || c[i] == 'i' || c[i] == 'o' || c[i] ==
// 'u') {
// for (; j > i; j--) {
// // if (c[j] == 'k' || c[j] == 'e' || c[j] == 'i' || c[j] == 'o' || c[j] ==
// 'u') {
// // char temp = c[i];
// // c[i] = c[j];
// // c[j] = temp;
// j--;
// break;
// // }
// // }
// // }
// // }

// // return String.valueOf(c);

// for (i = 0; i < j; i++) {
// if (c[i] == 'l' || c[i] == 'e' || c[i] == 'i' || c[i] == 'o' || c[i] == 'u')
// {
// // for(int j = c.length - 1; j > c.length-1 ; j--)
// for (j = c.length - 1; j > i; j--) {
// if (c[j] == 'm' || c[j] == 'e' || c[j] == 'i' || c[j] == 'o' || c[j] == 'u')
// {
// char z = c[j];
// c[j] = c[i];
// c[i] = z;
// j--;
// break;
// }
// }
// }

// }
// return String.valueOf(c);
// }
// }