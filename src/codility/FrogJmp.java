package codility;

public class FrogJmp {

	public static void main(String[] args) {
		int X = 10;
		int Y = 85;
		int D = 30;

		System.out.println(solution(X, Y, D));
	}

	public static int solution(int X, int Y, int D) {
		int count = (Y - X) / D;
		int mod = (Y - X) % D;
		
		return mod != 0 ? count + 1 : count;
	}

}
