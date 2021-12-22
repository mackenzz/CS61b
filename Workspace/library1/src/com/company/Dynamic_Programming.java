package com.company;

import java.util.*;

public class Dynamic_Programming {

    // # 88
    public static int calcMinimummCost(int[] nums) {
        // without memory
        return Math.min(helperWithoutMemory(nums, 1), helperWithoutMemory(nums, 2));
    }

    public static int helperWithoutMemory(int[] nums, int i) {
        // i stands for the number of steps that one is sway from the top
        if(i < 2) {
            return nums[i];
        }

        return Math.min(helperWithoutMemory(nums, i + 1), helperWithoutMemory(nums, i + 2));
    }

    // #88 with memory
    public static int calMinimumCostUsingMemo(int[] nums) {
        // use dp[i] to store the minumumm cost when one is i steps away from the top
        int[] dp = new int[nums.length + 1];

        for (int i = 1; i < nums.length + 1; i++) {
            if (i <= 2){
                dp[i] = nums[nums.length - i];
            }
            else {
                dp[i] = Math.min(nums[nums.length - i] + dp[1], nums[nums.length - i] + dp[2]);
            }
        }

        return dp[nums.length];
    }


    // ------------------------ single series ------------------------
    // #89
    public static int rob(int[] nums) {
        // dp[i] stands for the max one can get if rob from 0 to i
        // then can choose to rob [i] or not
        // dp[i] = max(dp[i-2] + nums[i], dp[i-1])

        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        if (nums.length > 1){
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < nums.length; i ++) {
                dp[i] = Math.max(dp[i-2] + nums[i], dp[i - 1]);
            }
        }
        return dp[nums.length - 1];

    }

    // #89-2: use 2d matrix
    public static int rob2DMatrix(int[] nums) {
        // dp[0][i] stands for the max gain if not entering i
        // dp[1][i] stands for the max gain if entering i
        // dp[0][i] = max(dp[0][i-1], dp[1][i-1]) -- enter i-1 or not enter
        // dp[1][i] = dp[0][i-1] + nums[i] -- if enter i, i-1 cannot be entered
        // dp[*][i] only needs the data of dp[*][i-1], so no need to record the whole dp matrix

        if (nums.length == 0)
            return 0;

        int[][] dp = new int[nums.length][nums.length];
        dp[0][0] = 0;
        dp[1][0] = nums[0];

        for (int i = 1; i < nums.length; i ++) {
            // only use dp[*][0] and dp[*][1] to save the memory
            dp[0][i%2] = Math.max(dp[0][(i-1) % 2], dp[1][(i-1) % 2]);
            dp[1][i%2] = dp[0][(i-1) % 2] + nums[i];
        }

        return Math.max(dp[0][(nums.length - 1) % 2], dp[1][(nums.length - 1) % 2]);
    }

    public static int minCost(int[] cost) {
        // dp[j][i] stands for the minimum cose when i-th house is painted with j color
        // j color: 0: r, 1: g; 2: b
        if (cost.length == 0)
            return 0;

        int dp[][] = new int[cost.length][cost.length];
        for (int j = 0; j < 3; j ++)
            dp[j][0] = cost[0];

        for (int i = 1; i < cost.length; i ++) {
            // dp[0][i % 2] = Math.min(dp[1][(i-1) % 2], dp[2][(i-1) % 2]) + cost[i];
            for (int j = 0; j < 3; j ++) {
                dp[j][i % 2] = Math.min(dp[(j-1) % 3][(i-1) % 2], dp[(j-2) % 3][(i-1) % 2]) + cost[i];
            }
        }

        return Math.min(dp[0][(cost.length - 1) % 2], Math.max(dp[1][(cost.length - 1) % 2],
                dp[2][(cost.length - 1) % 2]));
    }

    // #92
    public static int minFlips(String s) {
        if (s.length() == 0)
            return 0;

        int dp[][] = new int[s.length()][s.length()];
        dp[0][0] = s.charAt(0) == '0' ? 0 : 1;
        dp[1][0] = s.charAt(0) == '1' ? 0 : 1;

        for (int i = 1; i < s.length(); i ++) {
            dp[0][i%2] = dp[0][(i-1)%2] + s.charAt(i) == '0' ? 0 : 1;
            dp[1][i%2] = Math.min(dp[0][(i-1)%2], dp[1][(i-1)%2]) + s.charAt(i) == '1' ? 0 : 1;
        }

        return Math.min(dp[0][((s.length()) - 1) % 2], dp[1][((s.length()) - 1) % 2]);
    }

    // #93
    public static int getLongtestFibSequence(int[] A) {
        // dp[i][j] stands for max length at i when the second last FibSequence index = j
        if (A.length < 3) {
            return 0;
        }
        Map<Integer, Integer> indexmap = new HashMap<>();
        for (int i = 0; i< A.length; i ++) {
            indexmap.put(A[i], i);
        }

        int[][] dp = new int[A.length][A.length];
        int result = 2; // at least 2 for initial number
        for (int i = 1; i < A.length; i ++) {
            for (int j = 0; j < i; j ++) {
                // traverse every j if j < i
                int k = indexmap.getOrDefault(A[i] - A[j], -1);
                dp[i][j] = k >= 0 ? dp[j][k] + 1 : 2;

                result = Math.max(dp[i][j], result);
            }
        }

        return result > 2 ? result: 2;

    }

    // #94 **
    public static int minimumCut(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < isPalindrome.length; i ++) {
            for (int j = 0; j < isPalindrome[0].length; j ++) {
                isPalindrome[i][j] = false;
                if (s.charAt(i) == s.charAt(j) && (j <= i || isPalindrome[i+1][j-1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i ++) {
            if (isPalindrome[0][i]) {
                dp[i] = 0;
            }
            else {
                for (int j = 0; j < i; j ++) {
                    if(isPalindrome[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j-1] + 1);
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }


    // ------------------------ double series ------------------------
    // #95
    public static int getLongestCommon(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return 0;

        int[][] dp = new int[nums1.length][nums2.length];

        for (int i = 0; i < nums1.length; i ++) {
            for (int j = 0; j < nums2.length; j ++) {
                if (i == 0 && j == 0)
                    dp[i][j] = nums1[0] == nums2[0] ? 1 : 0;

                if (nums1[i] == nums2[j]) {
                     dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // since dp[i][*] is only dependent on dp[i-1][*], we can only store dp[0][*] and dp[1][*]
        return dp[nums1.length-1][nums2.length-1];
    }

    // #96
    public static boolean isInterLeave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); i ++) {
            dp[i][0] = s1.charAt(i) == s3.charAt(i) && dp[i-1][0];
        }
        for(int j = 1; j < s2.length(); j ++) {
            dp[0][j] = s2.charAt(j) == s3.charAt(j) && dp[0][j-1];
        }

        for (int i = 1; i <= s1.length(); i ++) {
            for(int j = 1; j <= s2.length(); j ++) {
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i-1))
                        || (dp[i][j-1] && s2.charAt(i-1) == s3.charAt(i-1));
            }
        }

        return dp[s1.length()][s2.length()];
    }

    // #97
    public static int countSubsequence(String s, String target) {
        if (s.length() < target.length()) {
            return 0;
        }

        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i ++) {
            if (i < target.length())
                dp[i] = 0;
            else if (i == target.length())
                dp[i] = s.substring(0, i) == target ? 1 : 0;
            else {
                dp[i] = dp[i-1];
                for (int j = 0; j < i; j ++) {
                    if (s.substring(0, j).concat(s.substring(j + 1, i)) == target)
                        dp[i] = dp[i-1] + 1;
                }
            }
        }

        return dp[s.length() - 1];
    }

    // ------------------------ matrix path ------------------------
    // #98
    public static int countPaths(int [][] matrix) {
        // dp[i][j] stands for the number of valid paths from [0][0] to [i][j]
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i ++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < matrix[0].length; j ++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[matrix.length - 1][matrix[0].length - 1];
    }

    // #101
    // if wants part of an array equals to the left part, then the sum of the array needs to be an even number
    // so the problem would be transfer to:
    // we can take any entry from the array, so that the sum of those entries is equal to sum/2
    public static boolean partitionHalfSum(int[] nums) {
        // dp[i][j] stands for if we can take the first i-th num from nums, so that the sum of them = j
        if (Arrays.stream(nums).sum() %2 != 0)
            return false;
        int target = Arrays.stream(nums).sum() / 2;
        boolean [][] dp = new boolean[nums.length][target];

        for (int i = 0; i < dp.length; i ++) {
            for (int j = 0; j < dp[0].length; j ++) {
                if (j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = false;
                else {
                    dp[i][j] = (dp[i-1][j] || dp[i-1][j - nums[i]]);
                }
            }
        }

        return dp[nums.length - 1][target];
    }




}















