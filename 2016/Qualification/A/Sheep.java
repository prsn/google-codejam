import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Sheep {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T, t = 1, i, N;
		T = Integer.parseInt(br.readLine());
		while (t <= T) {
			N = Integer.parseInt(br.readLine());
			System.out.println(String.format("Case #%d: ", t) + getResult(N));
			t++;
		}
	}

	private static String getResult(int n) {
		if (n == 0) {
			return "INSOMNIA";
		}
		return String.valueOf(generateCount(n));
	}

	private static long generateCount(int n) {
		long res = n;
		int index = 1;
		Map<Long, Boolean> lists = new HashMap<Long, Boolean>();
		while (lists.size() < 10) {
			long running = res;
			while (running != 0) {
				lists.put(running % 10, true);
				running = running / 10;
			}
			index++;
			res = n * index;
		}
		index--;
		return n * index;
	}

}
