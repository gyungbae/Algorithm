import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPriorityQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            String[] arr = operation.split(" ");

            String command = arr[0];
            int number = Integer.parseInt(arr[1]);

            if (command.equals("I")) {
                minPriorityQueue.offer(number);
                maxPriorityQueue.offer(number);
            } else {
                if (minPriorityQueue.isEmpty()) {
                    continue;
                }

                if (number == 1) {
                    int maxNumber = maxPriorityQueue.poll();
                    minPriorityQueue.remove(maxNumber);
                } else {
                    int minNumber = minPriorityQueue.poll();
                    maxPriorityQueue.remove(minNumber);
                }
            }
        }

        if (minPriorityQueue.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{maxPriorityQueue.peek(), minPriorityQueue.peek()};
    }
}
