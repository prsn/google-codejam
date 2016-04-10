import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CoinJam {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T, t=1, i;
		int n, j, k;
		String values[];
		String coin;
		/*Map<Integer, List<String>> index = new HashMap<Integer, List<String>>();
		long start = System.currentTimeMillis();
		for( i = 2; i <= 32; i++) {
			index.put(i, getCoinjam(i));
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end - start));*/
		T = Integer.parseInt(br.readLine());
		while (t <= T) {
			values = br.readLine().split(" ");
			n = Integer.parseInt(values[0]);
			j = Integer.parseInt(values[1]);
			System.out.println(String.format("Case #%d:", t));
			List<String> coinjam = getCoinjam(n);
			for ( k=0; k < j; k++ ) {
				coin = coinjam.get(k);
				System.out.println(getDevisors(coin));
			}
			t++;
		}
	}
	
	private static String getDevisors(String coin) {
		StringBuffer val = new StringBuffer(coin);
		BigInteger value = null;
		BigInteger one = new BigInteger("1");
		BigInteger zero = new BigInteger("0");
		for ( int i = 2; i <= 10; i++ ) {
			value = new BigInteger(coin, i);
			for ( BigInteger divisor = new BigInteger("2"); divisor.compareTo(value) == -1; divisor = divisor.add(one) ) {
				if ( value.divideAndRemainder(divisor)[1].compareTo(zero) == 0) {
					val.append(" ").append(divisor.toString());
					break;
				}
			}
		}
		return val.toString();
	}

	private static List<String> getCoinjam(int length) {
		List<String> coinjams = new ArrayList<String>();
		length = length -2; // as every coinjam will surround by 1
		int rows = (int) Math.pow(2, length);
		int matrix[][] = new int[rows][length];
		int col, row, flipValue, bit;
		StringBuffer coinjam;
		for ( col = length - 1; col >= 0; col--) {
			flipValue = (int) Math.pow(2,length - col - 1);
			bit = 0;
			for ( row = 0; row < rows; row++ ) {
				if (row % flipValue == 0) {
					bit = 1 - bit;
				}
				matrix[row][col] = bit;
			}
		}
		for ( row = 0 ; row < rows; row++ ) {
			coinjam = new StringBuffer("1");
			for ( col = 0; col < length; col++) {
				coinjam.append(matrix[row][col]);
			}
			coinjam.append("1");
			if ( isValidCoinjam(coinjam)) {
				coinjams.add(coinjam.toString());
			}
		}
		return coinjams;
	}
	
	private static boolean isValidCoinjam(StringBuffer coinjam) {
		BigInteger value;
		String coin = coinjam.toString();
		for ( int radix = 2; radix <= 10; radix++ ) {
			value = new BigInteger(coin, radix);
			if (value.isProbablePrime(1)) {
				return false;
			}
		}
		return true;
	}

}
