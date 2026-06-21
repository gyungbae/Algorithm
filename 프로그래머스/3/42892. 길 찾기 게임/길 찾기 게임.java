import java.util.*;

class Solution {
    int size;

    void insert(TreeNode current, TreeNode newNode) {
        if(newNode.x < current.x) {
            if(current.left == null) {
                current.left = newNode; 
            } else {
                insert(current.left, newNode);
            }
        } else {
            if(current.right == null) {
                current.right = newNode; 
            } else {
                insert(current.right, newNode);
            }
        }
    }

    void preOrder(TreeNode current, List<Integer> list) {
        if(current == null)
            return;

        list.add(current.idx);
        preOrder(current.left, list); 
        preOrder(current.right, list); 
    }

    void postOrder(TreeNode current, List<Integer> list) {
        if(current == null)
            return;

        postOrder(current.left, list); 
        postOrder(current.right, list); 
        list.add(current.idx);
    }

    public int[][] solution(int[][] nodeinfo) {
        this.size = nodeinfo.length;

        List<TreeNode> nodeList = new ArrayList<>();

        for(int nodeIdx = 0; nodeIdx < size; nodeIdx++) {
            int x = nodeinfo[nodeIdx][0];
            int y = nodeinfo[nodeIdx][1];

            nodeList.add(new TreeNode(x, y, nodeIdx + 1));
        }

        nodeList.sort((node1, node2) -> {
            if(node1.y == node2.y)
                return node1.x - node2.x;

            return node2.y - node1.y;
        });

        TreeNode root = nodeList.get(0);

        for(int nodeIdx = 1; nodeIdx < size; nodeIdx++) {
            insert(root, nodeList.get(nodeIdx)); 
        }

        List<Integer> preOrderList = new ArrayList<>();
        preOrder(root, preOrderList);

        List<Integer> postOrderList = new ArrayList<>();
        postOrder(root, postOrderList);

        int[][] answer = new int[2][size];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}

class TreeNode {
    int x, y, idx;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x, int y, int idx) {
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}