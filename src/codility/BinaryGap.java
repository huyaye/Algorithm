package codility;

public class BinaryGap {
	public static void main(String[] args) {
		System.out.println(solution(32));
	}

	public static int solution(int N) {
		int result = 0;

		boolean checking = false;
		int length = 0;
		while (N > 0) {
			int k = N % 2;
			if (checking && k == 0) {
				++length;
			} else if (checking && k == 1) {
				result = Math.max(result, length);
				length = 0;
			} else if (checking == false && k == 1) {
				checking = true;
			}
			N = N / 2;
		}

		return result;
	}
}
