import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Integer> deliveryStack = new Stack<>();
        Stack<Integer> pickupStack = new Stack<>();

        for (int idx = 0; idx < n; idx++) {
            if (deliveries[idx] > 0) {
                deliveryStack.push(idx);
            }

            if (pickups[idx] > 0) {
                pickupStack.push(idx);
            }
        }

        long answer = 0;

        while (!deliveryStack.isEmpty() || !pickupStack.isEmpty()) {
            int farDelivery = deliveryStack.isEmpty() ? -1 : deliveryStack.peek();
            int farPickup = pickupStack.isEmpty() ? -1 : pickupStack.peek();

            int farHouse = Math.max(farDelivery, farPickup);
            answer += (long) (farHouse + 1) * 2;

            int deliveryBox = 0;
            while (!deliveryStack.isEmpty() && deliveryBox < cap) {
                int deliveryIdx = deliveryStack.peek();
                int possibleBox = cap - deliveryBox;

                if (deliveries[deliveryIdx] > possibleBox) {
                    deliveries[deliveryIdx] -= possibleBox;
                    deliveryBox = cap;
                } else {
                    deliveryBox += deliveries[deliveryIdx];
                    deliveries[deliveryIdx] = 0;
                    deliveryStack.pop();
                }
            }

            int pickupBox = 0;
            while (!pickupStack.isEmpty() && pickupBox < cap) {
                int pickupIdx = pickupStack.peek();
                int possibleBox = cap - pickupBox;

                if (pickups[pickupIdx] > possibleBox) {
                    pickups[pickupIdx] -= possibleBox;
                    pickupBox = cap;
                } else {
                    pickupBox += pickups[pickupIdx];
                    pickups[pickupIdx] = 0;
                    pickupStack.pop();
                }
            }
        }

        return answer;
    }
}