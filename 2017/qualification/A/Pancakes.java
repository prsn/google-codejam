import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by prasun on 8/4/17.
 */
public class Pancakes {
    public static void main(String[] args) throws NumberFormatException,
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, t = 1, i, N;
        String [] vals;
        T = Integer.parseInt(br.readLine());
        while (t <= T) {
            vals = br.readLine().split(" ");
            N = Integer.parseInt(vals[1]);
            System.out.println(String.format("Case #%d: %s", t, getResult(vals[0], N)));
            t++;
        }
    }
    static String getResult(String row, int k) {
        int n = row.length();
        char[] arr = row.toCharArray();
        int steps = 0;
        for(int i=0; i <= n-k;i++) {
            if (arr[i] == '-') {
                steps++;
                flip(arr, i, i + k - 1);
            }
        }
        for(int i = n-1; i>= n-k ;i--)
            if(arr[i] == '-')
                return "IMPOSSIBLE";
        return String.valueOf(steps);
    }
    static void flip(char[] ch, int start, int k) {
        while(start<=k) {
            ch[start] = ch[start] == '-' ? '+' : '-';
            start++;
        }
    }

    static String getResultBFS(String row, int k) {
        Set<Node> seenNodes = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node node = new Node(row, 0);
        seenNodes.add(node);
        queue.add(node);
        int n = row.length();
        while(queue.size()!=0) {
            node = queue.poll();
            if(node.isFinalState())
                return String.valueOf(node.level);
            for(int i=0; i <= n-k; i++) {
                Node newNode = new Node(generateNewNode(node.value, i, i + k - 1), node.level + 1);
                if(!seenNodes.contains(newNode)) {
                    queue.add(newNode);
                    seenNodes.add(newNode);
                }
            }
        }
        return "IMPOSSIBLE";
    }
    static String generateNewNode(String val, int start, int end) {
        char[] arrs = val.toCharArray();
        for(int i = start; i<=end; i++) {
            arrs[i] = arrs[i] == '-' ? '+' : '-';
        }
        return new String(arrs);
    }
    static class Node {
        private String value;
        private int level;

        Node(String val, int level) {
            this.value = val;
            this.level = level;
        }
        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            return this.value.equals(((Node)that).value);
        }

        public boolean isFinalState() {
            for(Character ch : value.toCharArray()) {
                if (ch == '-')
                    return false;
            }
            return true;
        }
    }
}
