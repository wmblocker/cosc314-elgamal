import java.util.*;
public class Main {

	public static void main(String[] args) {
		//System.out.println(31*43 + 38);
		test1();
		test2();
		test3();
	}
	
	public static void test1() {
		TextConverter converter = new TextConverter();

		int x = converter.convertToNum("dog");
		int root = 3;
		int mod = 43;
		int bobRandom = 26;
		int aliceRandom = 7;
		
		Key ct = encrypt(x, root, mod, aliceRandom, bobRandom);
		System.out.println("Ciphertext: " + ct.toString());
		String pt = decrypt(ct, root, mod, aliceRandom);
		System.out.println(pt);
	}
	
	public static void test2() {
		Scanner input = new Scanner(System.in);
		TextConverter converter = new TextConverter();
		String text = "";

		System.out.println("Enter some text to encrypt: ");
		text = input.nextLine();
		
		int x = converter.convertToNum(text);
		
		System.out.println("Enter a root: ");
		int root = input.nextInt();
		
		System.out.println("Enter a prime: ");
		int mod = input.nextInt();
		
		System.out.println("What is Bob's random number?");
		int bobRandom = input.nextInt();
		
		System.out.println("What is Alice's random number");
		int aliceRandom = input.nextInt();
		
		System.out.println("Text converted to number: " + x);
		Key ct = encrypt(x, root, mod, aliceRandom, bobRandom);
		System.out.println("Ciphertext: " + ct.toString());
		String pt = decrypt(ct, root, mod, aliceRandom);
		System.out.println("Plaintext: "+pt);
	}
	
	
	public static void test3() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter two numbers to decrypt: ");
		
		int x = input.nextInt();
		int y = input.nextInt();
		
		Key ct = new Key(x, y);
		
		int root = 31847;

		int mod = 18074;

		int aliceRandom = 5;

		String pt = decrypt(ct, root, mod, aliceRandom);
		System.out.println("Plaintext: "+pt);
	}
	

	public static Key encrypt(int x, int root, int mod, int aliceRandom, int bobRandom) {
		//The powers of the root are stored in a HashMap
		HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		RepeatedSquare repeat1 = new RepeatedSquare(root, mod);
		
		int aliceKey = 0;
		
		//The Ciphertext pair
		int y1 = 0;
		int y2 = 0;
		
		//Making sure that that plaintext that is converted to a number is mod 43
		x = x % mod;
		
		System.out.println("The plaintext converted into a number and mod "+mod+" is "+ x);
		
		//Map the powers that are stored in the RepeatedSquare class to the HashMap.
		map1 = repeat1.mapValues();
		
		//The first value in the ciphertext pair, y1 = root to the power of the random number chosen
		y1 = map1.get(bobRandom);
		
		//Find Alice's Key in the HashMap of the powers of the root
		aliceKey = map1.get(aliceRandom);
		
		//This map is to store the powers of Alice's key 
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		RepeatedSquare repeat2 = new RepeatedSquare(aliceKey, mod);
		
		//Map the powers that are stored in the RepeatedSquare class to the HashMap.
		map2 = repeat2.mapValues();
		
		//After populating the second Hashmap which has the powers of Alice's Key
		//Search for the power of Bob's Random in that map
		//After finding that value, multiply it by the plaintext
		y2 = (map2.get(bobRandom) * x) % mod;
		
		//Store the values of y1, y2 into a key object.
		Key cipher = new Key(y1, y2);
		return cipher;
	}
	
	public static String decrypt(Key cipher, int root, int mod, int aliceRandom) {
		//The powers of the root are stored in a HashMap
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		TextConverter converter = new TextConverter();
		
		//Find the powers of the y1 in the ciphertext
		RepeatedSquare repeat = new RepeatedSquare(cipher.getY1(), mod);
		Euclidean euclid = new Euclidean();
		
		//Map the powers that are stored in the RepeatedSquare class to the HashMap.
		map = repeat.mapValues();
		
		//Get the value of y1 to the power of Alice's Random number and find the inverse of that number
		int y = map.get(aliceRandom);
		y = euclid.findInverse(y, mod);
		
		//Multiply the inverse found by y2
		y = y * cipher.y2;
		
		//System.out.println(y);
		
		//It must be mod p 
		y = y % mod;
		System.out.println("After finding the powers of y1 of the CT pair, and finding the inverse value of Alices random number"
				+ "\n mod p -  the result is "+y);
		
		//Map the powers of y mod p 
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		RepeatedSquare repeat2 = new RepeatedSquare(y, mod);
		
		map2 = repeat2.mapValues();
		
		//System.out.println(map2);
		//Trying to retrieve the values necessary to equal the original number that was converted from the text.
		
		int temp = y;
		
		//This worked for DOG
		y = map2.get(y) + y;

		y = y * mod + temp;

		return (converter.convertToText(y));
	}
	
	
}
