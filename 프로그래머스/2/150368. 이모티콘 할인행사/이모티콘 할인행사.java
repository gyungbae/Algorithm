class Solution {
    int n, m;
    int[][] users;
    int[] emoticons;
    
    int[] ratio = {10, 20, 30, 40};
    int[] discounts;
    int maxUser, maxProfit;
    
    void makeDiscount(int discountIdx) {
        if(discountIdx == m) {
            update();
            return;
        }
        
        for(int ratioIdx = 0; ratioIdx < 4; ratioIdx++) {
            discounts[discountIdx] = ratio[ratioIdx];
            makeDiscount(discountIdx + 1);
            discounts[discountIdx] = 0;
        }
    }
    
    void update() {
        int user = 0;
        int profit = 0;
        for(int userIdx = 0; userIdx < n; userIdx++) {
            int[] info = users[userIdx];
            
            int sum = 0;
            boolean subscribed = false;
            for(int emoticonIdx = 0; emoticonIdx < m; emoticonIdx++) {
                int discount = discounts[emoticonIdx];
                
                if(discount < info[0])
                    continue;
                
                int price = (int) emoticons[emoticonIdx] * (100 - discount) / 100;
                sum += price;
                
                if(sum >= info[1]) {
                    subscribed = true;
                    break;
                }
            }
            
            if(subscribed) {
                user++;
            } else {
                profit += sum;
            }
        }
        
        if(user > maxUser) {
            maxUser = user;
            maxProfit = profit;
        } else if(user == maxUser) {
            maxProfit = Math.max(maxProfit, profit);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        this.users = users;
        this.emoticons = emoticons;
        
        discounts = new int[m];
        makeDiscount(0);
        
        return new int[]{maxUser, maxProfit};
    }
}