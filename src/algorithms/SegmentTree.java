package algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SegmentTree {
	static long [] array;
	static long [] segmentTree;	// 구간합을 저장하고 있는 세그먼트트리

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("testcase/segmentsum.txt"));
//			br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			array = new long[N + 1];
			for (int i = 1; i <= N; i++) {
				array[i] = Integer.parseInt(br.readLine());
			}
			
			// 세그먼트트리 초기화
			int treeHeight = (int)(Math.ceil(Math.log(N) / Math.log(2))) + 1;
			int treeSize = (int)Math.pow(2, treeHeight);
			segmentTree = new long[treeSize];
			init(1, 1, N);

			// 쿼리
			for (int i = 0; i < M + K; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				if (A == 1) {
					long diff = array[B] - C;
					array[B] = C;
					update(1, 1, N, B, diff);
				} else if (A == 2) {
					System.out.println(sum(1, 1, N, B, C));
				}
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param node	세그먼트트리의 인덱스
	 * @param start, end  해당노드가 책임지고 있는 구간 (array 의 인덱스)
	 * @return 구간합
	 */
	private static long init(int node, int start, int end) {
		if (start == end) {
			segmentTree[node] = array[start];
		} else {
			long sumOfLeftChildren = init(node * 2, start, (start + end) / 2);
			long sumOfRightChildren = init(node * 2 + 1, (start + end) / 2 + 1, end);
			segmentTree[node] = sumOfLeftChildren + sumOfRightChildren;
		}
		return segmentTree[node];
	}
	
	/**
	 * 해당 세그먼트트리 노드가 관여하는 목적 구간 (left~right) 의 부분합을 리턴  
	 */
	private static long sum(int node, int start, int end, int left, int right) {
		if (end < left || start > right) {
			return 0;
		} else if (start >= left && right >= end) {
			return segmentTree[node];
		} else {
			long sumOfLeftChildren = sum(node * 2, start, (start + end) / 2, left, right);
			long sumOfRightChildren = sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
			return sumOfLeftChildren + sumOfRightChildren;
		}
	}

	/**
	 * target 이 포함된 모든 구간노드의 값을 diff 만큼 빼준다.
	 */
	private static void update(int node, int start, int end, int target, long diff) {
		if (target < start || target > end) {
			return;
		}
		segmentTree[node] -= diff;
		if (start != end) {
			update(node * 2, start, (start + end) / 2, target, diff);
			update(node * 2 + 1, (start + end) / 2 + 1, end, target, diff);
		}
	}	
}
