package codility;

public class CountDiv {

	public static void main(String[] args) {
		int A = 6;
		int B = 11;
		int K = 2;

		System.out.println(solution(A, B, K));
	}

	public static int solution(int A, int B, int K) {
		int startNum;
		int mod = A % K;
		if (mod == 0) {
			startNum = A;
		} else if (A > K) {
			startNum = K * ((A / K) + 1);
		} else {
			startNum = K;
		}

		int diff = B - startNum;
		if (diff < 0) {
			return 0;
		} else if (diff == 0) {
			return 1;
		} else {
			return 1 + (diff / K);
		}
	}

}
