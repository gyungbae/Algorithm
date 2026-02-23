import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

/**
 * EOF 처리
 * 전위 역추적 >> 트리 생성 >> 후위 순회
 */
public class Main {
    static StringBuilder sb = new StringBuilder();

    static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = new int[10000];
        int size = 0;
        while (true) {
            String line = br.readLine();

            if(line == null || line.equals(""))
                break;

            input[size++] = Integer.parseInt(line);
        }

        TreeNode root = new TreeNode(input[0]);
        for (int node = 1; node < size; node++) {
            root.insert(input[node]);
        }

        postOrder(root);

        System.out.println(sb);
    }
}

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    void insert(int value) {
        if (value < this.value) {
            if (this.left == null) {
                this.left = new TreeNode(value);
            } else {
                this.left.insert(value);
            }
        } else {
            if (this.right == null) {
                this.right = new TreeNode(value);
            } else {
                this.right.insert(value);
            }
        }
    }
}
