import java.util.*;

public class Euclidean {
	
	public int findInverse(int a, int mod) {		
			
		//Store the mod in a temp variable
        int m0 = mod;
        int quotient;
        int t;
        
        int x0 = 0; 
        	int x1 = 1;
     
        if (mod == 1 || mod == 0)
        return 0;
     
        while (a > 1)
        {
            // q is quotient
            quotient = a / mod;
            
            t = mod;

            // m is remainder now, process 
            // same as Euclid's algo
            mod = a % mod; 
            a = t;

     
            t = x0;
     
            x0 = x1 - quotient * x0;
            
            x1 = t;            

        }
     
        // Make x1 positive
        if (x1 < 0)
        x1 += m0;
        return x1;
	}
	

	
	
}
