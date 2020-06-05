package codility;

/*
 * https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
 */
public class FrogRiverOne {

	public static void main(String[] args) {
		int X = 5;
		int[] A = {1, 3, 1, 4, 2, 3, 5, 4};

		System.out.println(solution(X, A));
	}

	public static int solution(int X, int[] A) {
		int count = 0;
		boolean[] fallen = new boolean[X + 1];
		for (int i = 0; i < A.length; i++) {
			if (fallen[A[i]] == false) {
				fallen[A[i]] = true;
				++count;
				if (count == X) {
					return i;
				}
			}
		}
		return -1;
	}

}
