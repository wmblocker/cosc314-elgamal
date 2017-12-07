import java.util.*;
public class Main {

	
	public static int bobNum = 0;
	public static int aliceNum = 0;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		TextConverter converter = new TextConverter();
		String text = "";

		System.out.println("Enter some text to encrypt: ");
		text = input.nextLine();
		
		int x = 14;
		int p = 43;
		int root = 3;
		int mod = 43;
		int bobRandom = 26;
		int aliceRandom = 7;
		
		
		System.out.println("Text converted to number: " + converter.convertToNum(text));
		Key ct = encrypt(x, p, root, mod, aliceRandom, bobRandom);
		System.out.println("Ciphertext: " + ct.toString());
		String pt = decrypt(ct, x, p, root, mod, aliceRandom, bobRandom);
		
		System.out.println(pt);
		//System.out.println(122500 % 19);
		//System.out.println(ct.toString());
		//System.out.println(decrypt(ct, p, a, n, r, s));
	}
	

	public static Key encrypt(int x, int p, int root, int mod, int aliceRandom, int bobRandom) {
		HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		DLP dlp1 = new DLP();
		RepeatedSquare repeat1 = new RepeatedSquare(root, mod);
		
		int aliceKey = 0;
		int y1 = 0;
		int y2;
			
		map1 = repeat1.mapValues();
		y1 = map1.get(bobRandom);
	
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		DLP dlp2 = new DLP();
		RepeatedSquare repeat2 = new RepeatedSquare(root, mod);
		
		map2 = repeat2.mapValues();
		
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
		DLP dlp = new DLP();
		RepeatedSquare repeat = new RepeatedSquare(cipher.getY1(), 43);
		Euclidean euclid = new Euclidean();
		
		map = repeat.mapValues();
		
		int y = map.get(aliceRandom);
		
		y = euclid.findInverse(y, mod);
		y = y * cipher.y2;
		System.out.println(y % 43);
		
		return "";
	}
	
	
}
