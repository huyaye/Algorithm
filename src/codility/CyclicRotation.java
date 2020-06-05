package codility;

public class CyclicRotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int[] solution(int[] A, int K) {
		int[] result = new int[A.length];

		for (int i = 0; i < A.length; i++) {
			int index = (i + K) % A.length;
			result[index] = A[i];
		}

		return result;
	}
}
