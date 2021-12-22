package com.company;

import com.sun.jdi.connect.spi.TransportService;

import java.util.*;

public class BFS_DFS {
    public static int getmaxArea(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean visited[][] = new boolean[rows][cols];
        for (int i = 0; i < visited.length; i ++) {
            for (int j = 0; j < visited[0].length; j ++) {
                visited[i][j] = false;
            }
        }

        int maxArea = 0;

        for (int i = 0; i < visited.length; i ++) {
            for (int j = 0; j < visited[0].length; j ++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = getArea(grid, visited, i, j);
                    maxArea = Math.max(area, maxArea);
                }

            }
        }

        return maxArea;
    }

    public static int getArea(int[][] grid, boolean[][] visited, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        // if use stack here to replace queue, it will be DFS instead of BFS
//        Stack<int[]> stack = new Stack<>();
//        stack.push(new int[] {i, j});
        queue.add(new int[] {i, j});
        visited[i][j] = true;
        int area = 0;

        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] position = queue.remove();
            area ++;
            for (int[] direction: directions) {
                int rowNow = i + direction[0];
                int colNow = j + direction[1];
                if (rowNow >=0 && rowNow < grid.length
                        && colNow >= 0 && colNow < grid[0].length
                        && grid[rowNow][colNow] == 1 && !visited[rowNow][colNow]) {
                    queue.add(new int[] {rowNow, colNow});
                    visited[rowNow][colNow] = true;
                }

            }
        }

        return area;
    }

    // #107
    public static int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] distence = new int[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        // Initialize
        for (int i = 0; i < matrix.length; i ++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                if (matrix[i][j] == 0) {
                    distence[i][j] = 0;
                } else {
                    distence[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int [][] dirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            int [] pos = queue.remove();
            int dist = distence[pos[0]][pos[1]];
            for (int[] dir: dirs) {
                int r = pos[0] + dir[0];
                int c = pos[1] + dir[1];

                if (c >= 0 && c < matrix.length && r >= 0 && r <matrix.length) {
                    if (distence[r][c] != dist + 1) {
                        distence[r][c] = dist + 1;
                        queue.add(new int[] {r, c});
                    }
                }
            }
        }

        return distence;
    }

    // #108: word evolve
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        Set<String> notVisited = new HashSet<>(wordList);

        int length = 1;
        queue1.add(beginWord);

        while(!queue1.isEmpty()) {
            String word = queue1.remove();

            // return the result
            if(word.equals(endWord)) {
                return length;
            }

            // for every word in queue1, update queue2
            List<String> neighbors = getNeighbors(word);
            for (String neighbor: neighbors) {
                if (notVisited.contains(neighbor)) {
                    queue2.add(neighbor);
                    notVisited.remove(neighbor);
                }
            }

            // when queue1 is empty, replace queue1 with queue2
            if (queue1.isEmpty()) {
                length ++;
                queue1 = queue2;
                queue2 =  new LinkedList<>();
            }
        }
        return 0;
    }

    public static List<String> getNeighbors(String word) {
        List<String> neighbors = new LinkedList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i ++) {
            char old = chars[i];
            for (char ch = 'a'; ch < 'z'; ch++) {
                if (old != ch) {
                    chars[i] = ch;
                    neighbors.add(new String(chars));
                }
            }
            chars[i] = old;
        }
        return neighbors;
    }

    public static int doubleDirectionLadderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add(beginWord);
        set2.add(endWord);

        int length = 1;

        return 0;

    }

    // 109
    public static int openLock(String[] deadends, String target) {
        // start from "0000"
        for (String deadend: deadends) {
            if (target.equals(deadend) || "0000".equals(deadend)) {
                return -1;
            }
        }

        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        Set visited = new HashSet<>();
        Set deads = new HashSet<>(Arrays.asList(deadends));

        queue1.add("0000");
        int times = 0;

        while (!queue1.isEmpty()) {
            String combo = queue1.remove();

            if (combo.equals(target)) {
                return times;
            }

            List<String> neighbors = getNeighbors(combo);
            for (String neighbor: neighbors) {
                if(!visited.contains(neighbor) && !deads.contains(neighbor)) {
                    queue2.add(neighbor);
                    visited.add(neighbor);
                }
            }

            if (!queue1.isEmpty()) {
                queue1 = queue2;
                queue2.clear();
                times ++;
            }
        }

        return times;
    }
}






