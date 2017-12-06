import java.util.*;
import java.io.*;

public class DLP {
	
	public int search(HashMap<Integer, Integer> map, int k ) {
		int retVal = 0;
		for(int i = 1; i <= map.size(); i++) {
			if(map.get(i) == k) {
				retVal = i;
			}
		}
		return retVal;
	}

}