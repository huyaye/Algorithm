
/*
 * https://algospot.com/judge/problem/read/STRJOIN
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Strjoin {
	static int T; // 테스트케이스 갯수
	static int N; // 문자열 갯수

	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("testcase/strjoin.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int totalCost = 0;
			if (N > 1) {
				PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
				for (int i = 0; i < N; i++) {
					queue.add(sc.nextInt());
				}
				while (queue.size() > 1) {
					int cost = queue.remove() + queue.remove();
					totalCost += cost;
					queue.add(cost);
				}
			}
			System.out.println(totalCost);
		}
	}
}
