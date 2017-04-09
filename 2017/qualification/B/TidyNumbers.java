import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by prasun on 8/4/17.
 */
public class TidyNumbers {
    public static void main(String[] args) throws NumberFormatException,
            IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, t = 1, i;
        long N;
        T = Integer.parseInt(br.readLine());
        while (t <= T) {
            N = Long.parseLong(br.readLine());
            System.out.println(String.format("Case #%d: ", t) + getResult(N));
            t++;
        }
    }
    static long getResult(long n) {
        while(!isTidy(n)) {
        long out=0;
            int len = (int)Math.log10(n);
            long div = (long) Math.pow(10, len);
            int lastSeen = (int) (n / div);
            n = n % div;
            div = div / 10;
            while(div!=0) {
                int digit = (int) (n / div);
                if (digit < lastSeen) {
                    out = out * 10 + lastSeen - 1;
                    while(div!=0) {
                        out = out * 10 + 9;
                        div = div / 10;
                    }
                    n = out;
                    break;
                }
                out = out * 10 + lastSeen;
                lastSeen = digit;
                n = n % div;
                div = div / 10;
            }
        }
        return n;
    }
    static boolean isTidy(long n) {
        int lastSeen = 0;
        int len = (int)Math.log10(n);
        long div = (long) Math.pow(10, len);
        while(div!=0) {
            int digit = (int) (n / div);
            if ( digit < lastSeen)
                return false;
            lastSeen = digit;
            n = n % div;
            div = div / 10;
        }
        return true;
    }
}
