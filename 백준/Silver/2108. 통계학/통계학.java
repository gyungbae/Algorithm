import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputArr;

    static Map<Integer, Integer> countMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inputArr = new int[N];
        double sum = 0;
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(br.readLine());
            inputArr[idx] = num;
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            sum += num;
        }
        Arrays.sort(inputArr);



        StringBuilder sb = new StringBuilder();
        sb.append(Math.round(sum / N)).append("\n");
        sb.append(inputArr[N / 2]).append("\n");

        int maxCount = 0;
        for (int count : countMap.values()) {
            maxCount = Math.max(count, maxCount);
        }

        List<Integer> maxCountList = new ArrayList<>();
        for (int key : countMap.keySet()) {
            if (countMap.get(key) == maxCount) {
                maxCountList.add(key);
            }
        }
        Collections.sort(maxCountList);

        sb.append(maxCountList.size() > 1 ? maxCountList.get(1) : maxCountList.get(0)).append("\n");
        sb.append(inputArr[N - 1] - inputArr[0]).append("\n");

        System.out.println(sb);
    }
}
