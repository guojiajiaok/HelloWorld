package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int n = in.nextInt();
            int[] arr = new int[n];
            List<Integer> lists = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            for(int i = n - 1; i >= 0; --i) {
                if(!lists.contains(arr[i])) {
                    lists.add(arr[i]);
                }
            }
            for(int i = lists.size() - 1; i >= 0; --i) {
                System.out.print(lists.get(i));
                if(i != 0) {
                    System.out.print(" ");
                }
            }
        }
        in.close();
    }
}
