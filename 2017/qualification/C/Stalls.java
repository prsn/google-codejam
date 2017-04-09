import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by prasun on 8/4/17.
 */
public class Stalls {
    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
        public static void main(String[] args) throws NumberFormatException,
                IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T, t = 1, i;
            long N, K;
            String[] values;
            T = Integer.parseInt(br.readLine());
            while (t <= T) {
                values = br.readLine().split(" ");
                N = Long.parseLong(values[0]);
                K = Long.parseLong(values[1]);
                Point point = getResult(N, K);
                System.out.println(String.format("Case #%d: ", t) + point.x + " " + point.y);
                t++;
            }
        }
        static Point getResult(long n, long k) {
            long processed = 0;
            Set<Long> seenNodes = new HashSet<>();
            PriorityQueue<Long> queue = new PriorityQueue<Long>(new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    if ( o2 > o1)
                        return 1;
                    if ( o2 < o1 )
                        return -1;
                    return 0;
                }
            });
            queue.add(n);
            while(!queue.isEmpty()) {
                Long val = queue.poll();
                processed++;
                if ( processed == k ) {
                    if( val % 2 == 1)
                        return new Point(val/2, val/2);
                    else
                        return new Point(val / 2, val / 2 - 1);
                }
                if ( val / 2 != 0) {
                    queue.add(val / 2);
                    if ( val % 2 == 1 ) {
                        queue.add(val / 2);
                    } else if (val / 2 -1 != 0) {
                        queue.add(val / 2 - 1 );
                    }
                }
            }
            return null;
        }
}
