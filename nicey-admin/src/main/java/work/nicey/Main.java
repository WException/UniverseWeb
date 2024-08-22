package work.nicey;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println();
    }
    public static int maxReward(int[] rewardValues) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < rewardValues.length; i++) {
            maxHeap.add(new int[]{rewardValues[i], i});
        }

        int x = 0;
        boolean[] selected = new boolean[rewardValues.length];

        while (!maxHeap.isEmpty()) {
            int[] entry = maxHeap.poll();
            int reward = entry[0];
            int i = entry[1];
            if (!selected[i]) {
                if (reward > x) {
                    x += reward;
                    selected[i] = true;
                } else {
                    break;
                }
            }
        }
        return x;
    }

    public int valueAfterKSeconds(int n, int k) {
        long[] nums = new long[n];
        Arrays.fill(nums, 1);
        while (k-- > 0) {
            long sum = nums[0];
            for (int i = 1; i < n; i++) {
                sum += nums[i] % 1000000007;
                nums[i] = sum % 1000000007;
            }
        }
        return Integer.parseInt(String.valueOf(nums[n - 1] % 1000000007));
    }

    public static int findWinner(int[] skills, int k) {
        int n = skills.length;
        Queue<Integer> queue = new LinkedList<>();
        int[] wins = new int[n];
        for (int i = 0; i < n; i++) queue.offer(i);

        while (true) {
            int p1 = queue.poll();
            int p2 = queue.poll();
            int winner = skills[p1] > skills[p2] ? p1 : p2;
            int loser = skills[p1] > skills[p2] ? p2 : p1;
            wins[winner]++;
            if (wins[winner] == k) return winner;
            queue.offer(winner);
            queue.offer(loser);
        }
    }

    public static String removeDigits(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        for (Character c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }
}
