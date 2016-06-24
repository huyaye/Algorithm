/*
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1023&sca=3050
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MarbleGame {
	static int T = 5; // 테스트케이스 갯수
	static int[] B = new int[3]; // 1 <= B[0] < B[1] < B[2] <= 30
	static int[] K = new int[2]; // 1 <= K[0], K[1] <= 500

	static char[][] winner; // 구슬개수별 승자 (첫번째 통의 구슬갯수, 두번째 통의 구슬갯수)

	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("testcase/marblegame.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(System.in);

		// 한번에 꺼낼수 있는 구슬의 수 3가지 입력
		for (int i = 0; i < 3; i++) {
			B[i] = sc.nextInt();
		}
		for (int test_case = 1; test_case <= T; test_case++) {
			// 두 통속에 들어있는 구슬의 수 입력
			for (int i = 0; i < 2; i++) {
				K[i] = sc.nextInt();
			}
			winner = new char[K[0] + 1][K[1] + 1];

			/*
			 * Logic
			 */
			for (int i = 0; i <= K[0]; i++) { // 첫번째 구슬통
				LOOP: for (int j = 0; j <= K[1]; j++) { // 두번째 구슬통
					winner[i][j] = 'B';
					for (int b = 0; b < 3; b++) { // 세가지 방법만큼 루프
						// A가 첫턴에 구슬을 빼고 난 후, 남아 있는 구슬의 조합의 승자를 조회.
						// 조합중 하나라도 B가 승자인것이 있다면 A가 무조건 승리할 수 있는 방법이 있다는 의미.
						// 조합의 승자가 모두 A라면 A가 승리할 수 있는 방법이 없다는 것을 의미.
						if (i - B[b] >= 0 && winner[i - B[b]][j] == 'B'
								|| j - B[b] >= 0 && winner[i][j - B[b]] == 'B') {
							winner[i][j] = 'A';
							continue LOOP;
						}
					}
				}
			}
			/*
			 * Result
			 */
			System.out.println(winner[K[0]][K[1]]);
		}
	}
}
