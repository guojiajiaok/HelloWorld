package algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class LuckyBags {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++)
                nums[i] = scanner.nextInt();

            Arrays.sort(nums);
            System.out.println(find(nums, 0, 0, 1));
        }
    }


    private static int find(int[] nums, int index, long sum, long multi) {
        int count = 0;
        for(int i=index; i<nums.length; i++) {
            sum += nums[i];
            multi = multi * nums[i];
            if(sum > multi)
                count += 1 + find(nums, i+1, sum, multi);
            else if(nums[i] == 1)
//          处理待判断的序列第一个数为1的时候，
//          那个1虽然不满足和大于积，但是要保留，继续往下考虑
                count += find(nums, i+1, sum, multi);
            else
//          现组合不能出现符合幸运条件，直接跳到下一位
                break;
            sum -= nums[i];
            multi /= nums[i];
//          重复的数值结果相同，加一跳过
            while (i<nums.length-1 && nums[i]==nums[i+1])
                i++;
        }
        return count;
    }
}
