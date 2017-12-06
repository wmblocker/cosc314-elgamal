
public class TextConverter {
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
	
}
