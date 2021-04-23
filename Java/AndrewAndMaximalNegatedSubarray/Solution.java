package AndrewAndMaximalNegatedSubarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* Submission by: Ankur Paul
Passing Batch: 2023
ID: 11901599
https://www.hackerrank.com/AnkurPaul */

public class Solution {
    ArrayList<Integer> arrayStore;
    ArrayList<ArrayList<Integer>> filteredStore;
    int negativeCounts;

    Solution() {
        arrayStore = new ArrayList<>();
        filteredStore = new ArrayList<>();
        negativeCounts = 99999;
    }

    // Returns true if number is prime else false.
    public boolean checkPrime(int num) {
        boolean flag = false;
        for (int i = 2; i <= num / 2; ++i) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }
        return !flag ? true : false;
    }

    public void generateSubArrays(int size) {
        for (int startPoint = 0; startPoint < size; ++startPoint) {
            for (int grps = startPoint; grps < size; ++grps) {
                ArrayList<Integer> tempArrStore = new ArrayList<>();
                for (int c = startPoint; c <= grps; ++c) {
                    tempArrStore.add(arrayStore.get(c));
                }
                // System.out.println();
                boolean firstCheck = checkPrime(tempArrStore.get(0));
                boolean lastCheck = checkPrime(tempArrStore.get(tempArrStore.size() - 1));
                if (firstCheck == true && lastCheck == true) {
                    analyzeSubArray(tempArrStore);
                }
            }
        }
    }

    public void analyzeSubArray(ArrayList<Integer> input) {
        int count = 0;
        for (int looper : input) {
            if (looper < 0) // Counting the negative numbers
                ++count;
        }
        if (count < negativeCounts) {
            negativeCounts = count;
            filteredStore.clear();
            filteredStore.add(input);
        }
        if (count == negativeCounts) {
            filteredStore.add(input);
        }
        // System.out.println(input.toString() + " COUNT = " + count + " NEGATIVE
        // COUNTS: " + negativeCounts);
    }

    public void calculateLargestSubArray() {
        int largestSize = 0;
        // Calculates the length of the largest sub array with 0 minimum negative prime
        // numbers
        for (ArrayList<Integer> looper : filteredStore)
            largestSize = looper.size() > largestSize ? looper.size() : largestSize;
        System.out.println(largestSize);
    }

    public static void main(String[] args) throws IOException {
        Solution object = new Solution();

        int num = 0;
        String str = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        num = Integer.parseInt(bufferedReader.readLine());
        str = bufferedReader.readLine();

        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            int eachNum = Integer.parseInt(st.nextToken());
            object.arrayStore.add(eachNum);
        }
        // System.out.println(object.arrayStore.toString());
        object.generateSubArrays(num);
        // System.out.println("FILTERED: " + object.filteredStore.toString());
        object.calculateLargestSubArray();
    }
}