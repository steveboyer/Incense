package net.sereko.incense;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by steve on 2/9/17.
 */
public class TesterTest {

    @Test
    public void merge0(){
        int[] nums = merge(new int[0], new int[0]);
        Assert.assertArrayEquals(new int[0], nums);

        int[] nums1 = {0};
        int[] nums2 = merge(nums1, new int[0]);
        Assert.assertArrayEquals(nums1, nums2);

        int[] nums3 = {0};
        int[] nums4 = {1};
        int[] nums5 = {0,1};
        Assert.assertArrayEquals(nums5, merge(nums3, nums4));
    }


    @Test
    public void testApex() throws Exception {
        int[] numbers = {0,2,3,4,1,2,3,5,6,39,3,5,3,1,99,1,29,92,45,46,57};
        int[] numbers1 = {0,1};
        int[] numbers2 = {0,1};
        print(merge(numbers1, numbers2));

        print(mergesort(numbers));
    }


//
    public int[] mergesort(int[] numbers){
        switch(numbers.length){
            case 0:
                return numbers;
            case 1:
                return numbers;
            case 2:
                return numbers[0] < numbers[1] ? numbers : swap(numbers);
            default:
                return merge(mergesort(Arrays.copyOfRange(numbers, 0, numbers.length/2)), mergesort(Arrays.copyOfRange(numbers, numbers.length/2 + 1, numbers.length - 1)));
        }
    }

//    public int[] quicksort(int[] numbers){
//
//    }
//
//    public int[] bubblesort(int[] numbers){
//
//    }
//
    public int[] merge(int[] nums1, int[] nums2){
        if(nums1.length == 0 && nums2.length == 0){
            return new int[0];
        } else if(nums1.length == 0 || nums2.length == 0){
            return nums1.length == 0 ? nums2 : nums1;
        } else if(nums1.length <= 2 && nums2.length <= 2) {
            int length = nums1.length + nums2.length;
            int[] nums3 = new int[length];
            int n1 = 0, n2 = 0;
            while (n1 < nums1.length && n2 < nums2.length) {
                if (nums1[n1] == nums2[n2]) {
                    nums3[n1 + n2] = nums1[n1];
                    n1++;
                    nums3[n1 + n2] = nums2[n2];
                    n2++;
                } else if (nums1[n1] < nums2[n2]) {
                    nums3[n1 + n2] = nums1[n1];
                    n1++;
                } else {
                    nums3[n1 + n2] = nums2[n2];
                    n2++;
                }
            }
            return nums3;
        }   else if (nums1.length <= 2 || nums2.length <= 2){
            return (nums1.length > 2 ? (merge(merge(Arrays.copyOfRange(nums1, 0, nums1.length / 2), Arrays.copyOfRange(nums1, nums1.length / 2, nums1.length + 1)), nums2)) : (merge(merge(Arrays.copyOfRange(nums1, 0, nums1.length / 2), Arrays.copyOfRange(nums2, nums2.length / 2 + 1, nums2.length - 1)), nums1)));
        }  else if(nums1.length > 2 && nums2.length > 2){
            return merge(merge(Arrays.copyOfRange(nums1, 0, nums1.length/2), Arrays.copyOfRange(nums1, nums1.length/2 + 1, nums1.length - 1)), merge(Arrays.copyOfRange(nums1, 0, nums1.length/2), Arrays.copyOfRange(nums1, nums1.length/2 + 1, nums1.length - 1)));
        } else if(nums1.length > 2 || nums2.length > 2) {
            return (nums1.length > 2 ? (merge(merge(Arrays.copyOfRange(nums1, 0, nums1.length / 2), Arrays.copyOfRange(nums1, nums1.length / 2, nums1.length + 1)), nums2)) : (merge(merge(Arrays.copyOfRange(nums1, 0, nums1.length / 2), Arrays.copyOfRange(nums2, nums2.length / 2 + 1, nums2.length - 1)), nums1)));
        } else {
            return new int[0];
        }
    }

    public int[] zeros(int timesToRepeat){
        return repeat(0, timesToRepeat);
    }

    public int[] repeat(int number, int timesToRepeat){
        int[] numbers = new int[timesToRepeat];
        for(int i = 0; i < timesToRepeat; i++){
            numbers[i] = number;
        }
        return numbers;
    }

    public int[] swap(int[] nums){ // {0,1} -> {1,0}
        nums[0] = nums[0] + nums[1]; // 0+1=1
        nums[1] = nums[0] - nums[1]; // 1-1=0
        nums[0] = nums[0] - nums[1]; // 1-0=-1

        return nums;
    }

    public void print(int[] numbers){
        for(int i = 0; i < numbers.length; i++) {
            if(i == 0) System.out.print("{");
            System.out.print(numbers[i]);
            if(i < numbers.length - 1) System.out.print(",");
            if(i == numbers.length - 1) System.out.println("}");
        }
    }





}