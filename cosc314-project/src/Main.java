import java.util.*;
public class Main {

	
	public static int bobNum = 0;
	public static int aliceNum = 0;
	
	public static void main(String[] args) {
		int root = 15;
		int mod = 26;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		DLP dlp = new DLP();
		Euclidean euclid = new Euclidean();
		RepeatedSquare repeat = new RepeatedSquare(root, mod);
		
		//map = repeat.mapValues();
		//System.out.println(dlp.search(map, 10));
		System.out.println(euclid.findInverse(root, mod));
		//System.out.println(convertToNum("DOG"));
		//convertToText(2398);
		int p = 43;
		int a = 3;
		int n = 43;
		int r = 26;
		int s = 7;
		int ct = 0;
		//dlp.findPowers();
		//System.out.println(122500 % 19);
		//System.out.println(encrypt(p, a, n, r, s));
		//System.out.println(decrypt(ct, p, a, n, r, s));
	}
	
	public static int convertToNum(String text) {
		text = text.toUpperCase();
		int retVal = 0;
		int count = 0;
		int letter = 0;
		int sum = 0;
		int textLength = text.length();
		while(count < text.length()) {
			letter = text.charAt(count) - 65;
			retVal = letter * (int) Math.pow(26, (textLength-1) - count);
			sum = sum + retVal;
			count++;
		}
		return sum;
	}
	
	public static void convertToText(int num) {
		StringBuilder builder = new StringBuilder();
		int val = 0;
		int count = 0;
		char letter = ' ';
		int sum = num % 26;
		
		while(count < 3) {
			val =  num / (int) Math.pow(26, count);
			if(val > 26) {
				val = val % 26;
			}
			letter = (char) (val + 65);
			builder.append(letter);
			sum = sum  + val * (int) Math.pow(26, count);
			System.out.println(sum);
			count++;
		}
		builder.reverse();
		System.out.println(builder.toString());
		
	}
	
	public static long encrypt(int p, int a, int n, int r, int s) {
		int bobKey = 0;
		int aliceKey = 0;
		long key = 0L;
		
		bobKey = (int)((Math.pow(a, r)) % n);
		bobNum = bobKey;
		aliceKey = (int)((Math.pow(a, s)) % n);
		aliceNum = aliceKey;
		key = (long)(Math.pow(37, 26)) % 43;
		
		return key % 43;
		
	}
	
	public static int decrypt(int ct, int p, int a, int n, int r, int s) {
		int bobKey = 0;
		int aliceKey = 0;
		int key = 0;
		
		bobKey = (int)(Math.pow(bobNum, 7)) % n;
		
		int count = 0;
		int num = 0;
		while(count != 43) {
			key = (bobKey * count) % 43;
			if(key == 1) {
				num = count;
				count = 43;
			}
			else {
				count++;
			}
		}
		
		return (num * ct) % 43;
	}
	
	
}
