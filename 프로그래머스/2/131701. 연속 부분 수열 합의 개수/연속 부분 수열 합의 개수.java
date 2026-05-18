import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;

        Set<Integer> set = new HashSet<>();

        for (int size = 1; size <= n; size++) {
            for (int from = 0; from < n; from++) {
                int sum = 0;

                for (int count = 0; count < size; count++) {
                    int elementIdx = (from + count) % n;
                    sum += elements[elementIdx];
                }

                set.add(sum);
            }
        }

        return set.size();
    }
}