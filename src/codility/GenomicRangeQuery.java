package codility;

/*
 * https://app.codility.com/programmers/lessons/5-prefix_sums/genomic_range_query/
 */
public class GenomicRangeQuery {

	public static void main(String[] args) {
		String S = "CAGCCTA";
		int[] P = {2, 5, 0};
		int[] Q = {4, 5, 6};

		int[] result = solution(S, P, Q);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	private static int[] segmentTree;
	private static int[] DNA;

	public static int[] solution(String S, int[] P, int[] Q) {
		int M = P.length;
		int N = S.length();

		DNA = new int[S.length()];
		char[] chars = S.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
				case 'A':
					DNA[i] = 1;
					break;
				case 'C':
					DNA[i] = 2;
					break;
				case 'G':
					DNA[i] = 3;
					break;
				case 'T':
					DNA[i] = 4;
					break;
			}
		}
		int treeHeight = (int) (Math.ceil(Math.log(N) / Math.log(2))) + 1;
		int treeSize = (int) Math.pow(2, treeHeight);
		segmentTree = new int[treeSize];
		init(1, 0, N - 1);

		int[] result = new int[M];
		for (int i = 0; i < M; i++) {
			result[i] = findMin(1, 0, N - 1, P[i], Q[i]);
		}
		return result;
	}

	private static int init(int node, int start, int end) {
		if (start == end) {
			segmentTree[node] = DNA[start];
		} else {
			int leftResult = init(node * 2, start, (start + end) / 2);
			int rightResult = init(node * 2 + 1, (start + end) / 2 + 1, end);
			segmentTree[node] = Math.min(leftResult, rightResult);
		}
		return segmentTree[node];
	}

	private static int findMin(int node, int start, int end, int left, int right) {
		if (end < left || start > right) {
			return Integer.MAX_VALUE;
		} else if (left <= start && end <= right) {
			return segmentTree[node];
		} else {
			int leftResult = findMin(node * 2, start, (start + end) / 2, left, right);
			int rightResult = findMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
			return Math.min(leftResult, rightResult);
		}
	}

}
