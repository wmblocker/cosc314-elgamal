
public class Key {
	public int y1;
	public int y2;
	
	public Key(int y1, int y2) {
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public int getY1() {
		return this.y1;
	}
	
	public int getY2() {
		return y2;
	}
	
	public String toString() {
		return getY1() + " " +getY2();
	}
	
}
