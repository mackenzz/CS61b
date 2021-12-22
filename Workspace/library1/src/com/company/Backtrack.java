package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Backtrack {

    // 79-1
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length == 0) {
            return result;
        }
        helperSubset( 0, nums, new LinkedList<>(), result);

        return result;
    }

    //79-2
    public static void helperSubset(int index, int[] nums, LinkedList<Integer> subset,
                                    List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(subset);
        }
        else if (index < nums.length) {
            // not add nums[index]
            helperSubset(index + 1, nums, subset, result);

            subset.add(nums[index]);
            helperSubset(index + 1, nums, subset, result);
            // need to go back to father point
            subset.removeLast();
        }
    }

    // #80-1
    public static List<List<Integer>> subsets(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        if (n == 0 || n < k) {
            return result;
        }
        helperNKSubset(n, k, 0, new LinkedList<>(), result);
        return result;
    }

    // #80-2
    public static void helperNKSubset(int n, int k, int index, LinkedList<Integer> subset,
                                      List<List<Integer>> result) {
        if (subset.size() == k) {
            result.add(subset);
        }
        else if (index <= n) {
            helperNKSubset(n, k, index + 1, subset, result);

            subset.add(index);
            helperNKSubset(n, k, index + 1, subset, result);
            subset.removeLast();
        }
    }

    // #81-1
    public static List<List<Integer>> allowDDuplicateSubset(int[] nums, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if (target == 0) {
            return result;
        }

        helperAllowDuplicateSubset(nums,  target, 0, new LinkedList<Integer>(), result);
        return result;
    }

    public static void helperAllowDuplicateSubset(int[] nums, int target, int i,
                                                  LinkedList<Integer> subset, List<List<Integer>> result) {
        if (target == 0) {
            result.add(subset);
        }
        else if (i < nums.length && target > 0) {
            helperAllowDuplicateSubset(nums, target, i+1, subset, result);

            subset.add(nums[i]);
            helperAllowDuplicateSubset(nums, target - nums[i], i, subset, result);
            subset.removeLast();
        }
    }

    // # 85
    public static List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        if(n == 0) {
            return result;
        }

        helperParenthesis(n, 0, 0, "", result);
        return result;
    }

    public static void helperParenthesis(int n, int left, int right, String parenthesis, List<String> result) {
        if (left == n && right ==0) {
            result.add(parenthesis);
        }

        if (left < n) {
            helperParenthesis(n, left + 1, right, parenthesis + "(", result);
        }

        if(right < n && right < left) {
            helperParenthesis(n, left, right + 1, parenthesis + ")", result);
        }
    }

}











