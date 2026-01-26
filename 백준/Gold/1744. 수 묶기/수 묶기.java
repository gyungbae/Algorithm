import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> positiveQueue = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> negativeQueue = new PriorityQueue<>();
        boolean hasZero = false;

        int answer = 0;
        for (int input = 1; input <= N; input++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                positiveQueue.offer(num);
            } else if(num < 0){
                negativeQueue.offer(num);
            } else if (num == 0) {
                hasZero = true;
            } else {
                answer++;
            }
        }

        int size = negativeQueue.size();
        if (size > 0) {
            if (size % 2 == 0) {
                while (!negativeQueue.isEmpty()) {
                    answer += (negativeQueue.poll() * negativeQueue.poll());
                }
            } else {
                while (negativeQueue.size() > 1) {
                    answer += (negativeQueue.poll() * negativeQueue.poll());
                }

                if(!hasZero)
                    answer += negativeQueue.poll();
            }
        }

        size = positiveQueue.size();
        if (size % 2 == 0) {
            while (!positiveQueue.isEmpty()) {
                answer += positiveQueue.poll() * positiveQueue.poll();
            }
        } else {
            while (positiveQueue.size() > 1) {
                answer += (positiveQueue.poll() * positiveQueue.poll());
            }

            answer += positiveQueue.poll();
        }

        System.out.println(answer);
    }
}
