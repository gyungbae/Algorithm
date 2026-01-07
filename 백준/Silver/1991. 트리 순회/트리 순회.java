import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Node[] tree;

    static StringBuilder sb = new StringBuilder();

    static void preOrder(Node node) {
        if (node == null) {
            return;
        }

        sb.append(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    static void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        sb.append(node.val);
        inOrder(node.right);
    }

    static void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.val);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new Node[26];
        for (int node = 0; node < 26; node++) {
            tree[node] = new Node((char) ('A' + node));
        }

        for (int input = 1; input <= N; input++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (left != '.') {
                tree[node - 'A'].left = tree[left - 'A'];
            }

            if (right != '.') {
                tree[node - 'A'].right = tree[right - 'A'];
            }
        }

        Node root = tree[0];

        preOrder(root);
        sb.append("\n");
        inOrder(root);
        sb.append("\n");
        postOrder(root);

        System.out.println(sb);
    }
}

class Node {
    char val;
    Node left;
    Node right;

    public Node(char val) {
        this.val = val;
    }
}
