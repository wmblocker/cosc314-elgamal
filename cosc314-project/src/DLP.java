import java.util.*;
import java.io.*;

public class DLP {
	public int prime;
	public int root;
	public int mod;
	public int resid;

	public DLP() {
		insertValues();
	}

	public void insertValues() {
		Scanner input = new Scanner(System.in);
		this.prime = 0;
		this.root = 3;
		this.mod = 71;
	}

	public void findPowers() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int result = 0;
		int i = 0;
			
		while(i < mod) {
			result = recursiveHelper(1, root, i);
			
			map.put(i + 1,(result % mod));
			i++;
		}
		showLogs(map);
	}
	
	public int recursiveHelper(int n, int root, int count) {
		n = n * root;
		if(n > root) {
			n = n % mod;
		}
		return count == 0 ? n : recursiveHelper(n, root, count - 1);
	}

	public void showLogs(HashMap<Integer, Integer> map) {
		System.out.println(map.toString());
	}

}