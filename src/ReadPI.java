/*
 * https://algospot.com/judge/problem/read/PI
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadPI {
	static int T; // 테스트케이스 갯수
	static int N; // 숫자개수
	static int[] INPUT; // 입력 수열
	static Map<Integer, Integer> difficulties; // key 인덱스이하 수열의 최소 난이도

	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("testcase/readpi.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			String pi = sc.next();
			N = pi.length();
			INPUT = new int[N];
			for (int i = 0; i < N; i++) {
				INPUT[i] = Character.getNumericValue(pi.charAt(i));
			}
			difficulties = new HashMap<Integer, Integer>();
			/*
			 * Result
			 */
			int minLevel = getMinDifficulty(0);
			System.out.println(minLevel);
		}
	}

	private static int getMinDifficulty(int start) {
		if (difficulties.get(start) != null) {
			return difficulties.get(start);
		}
		int difficulty = 0;
		int min = 0;
		for (int i = 3; i <= 5; i++) {
			int nextStart = start + i;
			if (difficulties.get(nextStart) != null) {
				difficulty = difficulties.get(nextStart) + getDifficulty(start, start + i - 1);
			} else if (nextStart + 2 < N && difficulties.get(nextStart) == null) {
				difficulty = getMinDifficulty(nextStart) + getDifficulty(start, start + i - 1);
			} else if (start + i == N) { // 마지막인덱스로 딱 떨어질때,
				difficulty = getDifficulty(start, start + i - 1);
			}

			// 최소 난이도 구하기
			if (min == 0) {
				min = difficulty;
			} else if (min > difficulty) {
				min = difficulty;
			}
		}
		difficulties.put(start, min);
		return min;
	}

	private static int getDifficulty(int start, int end) {
		int first = INPUT[start];
		int second = INPUT[start + 1];
		boolean isOne = false; // 동일
		boolean isTwo = false; // 1씩
		boolean isFour = false; // 반복
		boolean isFive = false; // 등차수열

		int step = second - first;
		if (step == 0) {
			isOne = true;
		} else if (step == 1 || step == -1) {
			isTwo = true;
			isFour = true;
		} else {
			isFour = true;
			isFive = true;
		}
		for (int i = start + 2; i <= end; i++) {
			if (isOne) {
				if (INPUT[i] != first) {
					isOne = false;
				}
			}
			if (isTwo) {
				if (INPUT[i] - INPUT[i - 1] != step) {
					isTwo = false;
				}
			}
			if (isFour) {
				if (INPUT[i] != INPUT[i - 2]) {
					isFour = false;
				}
			}
			if (isFive) {
				if (INPUT[i] - INPUT[i - 1] != step) {
					isFive = false;
				}
			}
		}

		int result;
		if (isOne) {
			result = 1;
		} else if (isTwo) {
			result = 2;
		} else if (isFour) {
			result = 4;
		} else if (isFive) {
			result = 5;
		} else {
			result = 10;
		}
		return result;
	}
}
