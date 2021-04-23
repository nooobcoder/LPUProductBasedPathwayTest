package LimakAndPalindromes;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* Submission by: Ankur Paul
Passing Batch: 2023
ID: 11901599
https://www.hackerrank.com/AnkurPaul */

public class Solution {

    final int SIZE = 26;
    char[] arr;
    int[] frq;
    ArrayList<String> permutations;

    Solution() {
        permutations = new ArrayList<>();
    }

    void bubbleSort() {
        int n = frq.length;
        int temp = 0;
        char tempChar = ' ';
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (frq[j - 1] < frq[j]) {
                    temp = frq[j - 1];
                    frq[j - 1] = frq[j];
                    frq[j] = temp;

                    tempChar = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tempChar;
                }
            }
        }
    }

    void printCharWithFreq(String str) {
        int n = str.length();
        int[] freq = new int[SIZE];
        for (int i = 0; i < n; i++)
            freq[str.charAt(i) - 'a']++;
        arr = new char[n];
        frq = new int[n];
        for (int i = 0; i < n; i++) {
            if (freq[str.charAt(i) - 'a'] != 0) {
                arr[i] = str.charAt(i);
                frq[i] = freq[str.charAt(i) - 'a'];
                freq[str.charAt(i) - 'a'] = 0;
            }
        }
        // for (int a = 0; a < n; ++a) {
        // System.out.print("[" + arr[a] + " -> " + frq[a] + "]");
        // }
        // System.out.println();
        // for (int a = 0; a < n; ++a) {
        // System.out.print("[" + arr[a] + " -> " + frq[a] + "]");
        // }
        // System.out.println();
    }

    void doMakePermutations() {
        int stopPoint = 0;
        for (int l = 0; l < frq.length; ++l) {
            if (frq[l] == 0) {
                stopPoint = l;
                break;
            }
        }
        for (int a = 0; a < stopPoint; ++a) {
            if (Character.isLetter(arr[a])) {
                if (frq[a] > 2) {
                    String make = String.valueOf(arr[a]) + String.valueOf(arr[a]) + String.valueOf(arr[a]);
                    permutations.add(make);
                }
                if (frq[a] >= 2) {
                    for (int b = 0; b < stopPoint; ++b) {
                        if (a != b) {
                            String make = String.valueOf(arr[a]) + String.valueOf(arr[b]) + String.valueOf(arr[a]);
                            permutations.add(make);
                        }
                    }
                }
            }
        }
        Collections.sort(permutations); // sort it in ascending order

    }

    public static void main(String[] args) throws IOException {
        Solution obj = new Solution();
        int num = 0;
        String str = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        num = Integer.parseInt(bufferedReader.readLine());
        str = bufferedReader.readLine();

        obj.printCharWithFreq(str);
        obj.bubbleSort();
        obj.doMakePermutations();
        System.out.println(obj.permutations.size());
        for (String loop : obj.permutations) {
            System.out.println(loop);
        }
    }
}