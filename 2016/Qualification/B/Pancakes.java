import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author prsn
 *
 */
public class Pancakes {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T, t=1, i;
		int pos, Nk, ans;
		char value[]; 
		int bValue[];
		boolean cont ;
		T = Integer.parseInt(br.readLine());
		while (t <= T) {
			value = br.readLine().toCharArray();
			bValue = new int[value.length];
			for ( i = 0; i < value.length ; i++) {
				bValue[i] = value[i] == '-' ? 0 : 1 ;
			}
			Nk = bValue.length - 1;
			ans = 0;
			cont = true;
			while ( cont ) {
				Nk = slice(bValue, Nk);
				if ( Nk == -1 ) {
					cont = false;
				} else {
					if (bValue[0] == 0 ) {
						flip(bValue, Nk);
						ans++;
					} else {
						pos = - 1;
						for ( i = 1; i <= Nk ; i++) {
							if ( bValue[i] == 1 ) {
								while( bValue[i] == 1){
									i++;
								}
								pos = i - 1;
								break;
							}
						}
						if (pos != -1) {
							flip(bValue, pos);
						} else {
							flip(bValue, 0);
						}
						flip(bValue, Nk);
						ans += 2;
					}
				}				
			}
			System.out.println(String.format("Case #%d: %d", t, ans));
			t++;
		}
	}
	
	/**
	 * Return the position of first 0 from end.
	 * @param arr
	 * @param start
	 * @param end
	 * @return position of first 
	 */
	private static int slice(int arr[], int end) {
		for ( int i = end; i >= 0; i--) {
			if (arr[i] == 0 )
				return i;
		}
		return -1;
	}
	
	private static void flip(int arr[], int end) {
		int temp, i;
		for ( i = 0; i < end / 2; i++) {
			temp = arr[i];
			arr[i] = arr[end - i];
			arr[end - i] = temp;
			arr[i] = 1 - arr[i];
			arr[end - i] = 1 - arr[end - i];
		}
		if ( (end + 1) % 2 == 0 ) {
			temp = arr[i];
			arr[i] = arr[end - i];
			arr[end - i] = temp;
			arr[i] = 1 - arr[i];
			arr[end - i] = 1 - arr[end - i];
		} else {
			arr[end / 2 ] = 1 - arr [end / 2];
		}
	}

}
