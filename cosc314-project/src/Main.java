import java.util.*;
public class Main {

	public static void main(String[] args) {
		TextConverter converter = new TextConverter();
		converter.convertToNum("dog");
		System.out.println(converter.convertToText(2398));
		System.out.println();
		test2();
	}
	
	public static void test1() {
		int x = 2398;
		int p = 43;
		int root = 3;
		int mod = 43;
		int bobRandom = 26;
		int aliceRandom = 7;
		
		Key ct = encrypt(x, p, root, mod, aliceRandom, bobRandom);
		//System.out.println("Ciphertext: " + ct.toString());
		String pt = decrypt(ct, x, p, root, mod, aliceRandom, bobRandom);
		//System.out.println(pt);
	}
	
	public static void test2() {
		Scanner input = new Scanner(System.in);
		TextConverter converter = new TextConverter();
//		String text = "";
//
//		System.out.println("Enter some text to encrypt: ");
//		text = input.nextLine();
//		
//		int x = converter.convertToNum(text);
//		
//		System.out.println("Enter a prime: ");
//		
//		int p = input.nextInt();
//		
//		System.out.println("Enter a root: ");
//		int root = input.nextInt();
//		
//		System.out.println("Enter a modulo: ");
//		int mod = input.nextInt();
//		
//		System.out.println("What is Bob's random number?");
//		int bobRandom = input.nextInt();
//		
//		System.out.println("What is Alice's random number");
//		int aliceRandom = input.nextInt();
//		
		int x = converter.convertToNum("dog");
		int p = 43;
		int root = 3;
		int mod = 43;
		int bobRandom = 26;
		int aliceRandom = 7;
		
		System.out.println("Text converted to number: " + x);
		Key ct = encrypt(x, p, root, mod, aliceRandom, bobRandom);
		System.out.println("Ciphertext: " + ct.toString());
		String pt = decrypt(ct, x, p, root, mod, aliceRandom, bobRandom);
		System.out.println("Plaintext: "+pt);
	}
	

	public static Key encrypt(int x, int p, int root, int mod, int aliceRandom, int bobRandom) {
		HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		DLP dlp1 = new DLP();
		RepeatedSquare repeat1 = new RepeatedSquare(root, mod);
		
		int aliceKey = 0;
		int y1 = 0;
		int y2 = 0;
		
		x = x % mod;
		
		System.out.println(x);
		map1 = repeat1.mapValues();
		
		y1 = map1.get(bobRandom);
	
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		
		map2 = repeat1.mapValues();
		aliceKey = map2.get(aliceRandom);
		
		
		HashMap<Integer, Integer> map3 = new HashMap<Integer, Integer>();
		DLP dlp3 = new DLP();
		RepeatedSquare repeat3 = new RepeatedSquare(aliceKey, mod);
		
		map3 = repeat3.mapValues();

		y2 = (map3.get(bobRandom) * x) % mod;
		Key cipher = new Key(y1, y2);
		return cipher;
	}
	
	public static String decrypt(Key cipher, int x, int p, int root, int mod, int aliceRandom, int bobRandom ) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		TextConverter converter = new TextConverter();
		DLP dlp = new DLP();
		RepeatedSquare repeat = new RepeatedSquare(cipher.getY1(), mod);
		Euclidean euclid = new Euclidean();
		
		map = repeat.mapValues();
		
		int y = map.get(aliceRandom);
		
		y = euclid.findInverse(y, mod);
		y = y * cipher.y2;

		y = y % mod;
		
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		RepeatedSquare repeat2 = new RepeatedSquare(y, mod);
		
		map2 = repeat2.mapValues();
		int temp = y;
		y = map2.get(y) + y;
		y = y * mod + temp;
		return (converter.convertToText(y));
	}
	
	
}
