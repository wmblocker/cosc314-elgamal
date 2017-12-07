import java.util.*;

public class RepeatedSquare {
	public int root;
	public int mod;

	
	public RepeatedSquare(int root, int mod) {
		this.root = root;
		this.mod = mod;
		mapValues();
	}

	public HashMap<Integer, Integer> mapValues() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int result = 0;
		int i = 0;
			
		//While the count is less than the mod, recursively populate the HashMap with the
		//powers of the root
		while(i < mod) {
			result = recursiveHelper(1, root, i);
			
			map.put(i + 1,(result % mod));
			i++;
		}
		return map;
	}
	
	public int recursiveHelper(int n, int root, int count) {
		//Multiply n by the root
		n = n * root;
		
		//if n is greater than mod 
		if(n > mod) {
			n = n % mod;
		}
		
		//if the count is not equal to 0 then continue, else return the value and map it.
		return count == 0 ? n : recursiveHelper(n, root, count - 1);
	}

	public void showLogs(HashMap<Integer, Integer> map) {
		System.out.println("Powers of :" + root + " " + map.toString());
	}
	
}
