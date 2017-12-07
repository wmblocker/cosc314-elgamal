
public class TextConverter {
	
	public int convertToNum(String text) {
	 
		text = text.toUpperCase();
		int retVal = 0;
		int count = 0;
		int letter = 0;
		int sum = 0;
		
		//Get the length of the text.
		int textLength = text.length();
		while(count < text.length()) {
			//Get the letter and convert it from ascii
			letter = text.charAt(count) - 65;
			
			//Raise it to the power at the count
			retVal = letter * (int) Math.pow(26, (textLength-1) - count);
			sum = sum + retVal;
			count++;
		}
		return sum;
	}
	
	public String convertToText(int num) {
		StringBuilder builder = new StringBuilder();
		int val = 0;
		int count = 0;
		char letter = ' ';
		
		//Get the remainder of the value
		int sum = num % 26;
		
		while(count < 3) {
			//Get the last number of the value and divide it by 26 raised to the power of the count
			val =  num / (int) Math.pow(26, count);
			
			//Make sure that number is mod 26
			if(val > 26) {
				val = val % 26;
			}
			
			//Convert the number back to Ascii
			letter = (char) (val + 65);
			
			//Append that letter to the StringBuilder
			builder.append(letter);
			sum = sum  + val * (int) Math.pow(26, count);
			count++;
		}
		
		
		builder.reverse();
		return builder.toString();
		
	}
	
}
