import java.util.*;
import java.io.*;

public class DLP {
	public int prime;
	public int root;
	public int mod;
	public int resid;
	
	public DLP (){
		insertValues();
		findPowers();
	}
	
	public void insertValues(){
		Scanner input = new Scanner(System.in);
		this.prime = 0;
		this.root = 14;
		this.mod = 9;
	}
	
	public void findPowers(){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i;
		for(i = 1; i < 7; i++){
			double result = 0;
			result = Math.pow(this.root, i);
			while(result > 19){
				result = result - 19;
			}
			map.put(i, (int)result);
		}
		showLogs(map);
	}
	
	public void showLogs(HashMap<Integer, Integer> map){
		//System.out.println("L();
	}
	
	
}