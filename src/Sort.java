
public class Sort {
	private static int[] input = { 6, 40, 32, 60, 1, 100, 89, 41, 57, 23, 1 };
	// private static int[] input = { 6 };
	// private static int[] input = {15, 20, 13, 30, 10, 25, 3, 24};

	public static void main(String[] args) {
		System.out.print("original : [ ");
		System.out.print(input[0]);
		for (int i = 1; i < input.length; i++) {
			System.out.print(", " + input[i]);
		}
		System.out.println(" ]");

		// selectionSort(input.length);
		// insertionSort(input.length);
		quickSort(input.length, 0, input.length - 1, 0);

		System.out.print("sort result : [ ");
		System.out.print(input[0]);
		for (int i = 1; i < input.length; i++) {
			System.out.print(", " + input[i]);
		}
		System.out.println(" ]");
	}

	private static void selectionSort(int N) {
		for (int i = 0; i < N - 1; i++) {
			int min = input[i];
			int targetIndex = i;
			for (int j = i + 1; j < N; j++) {
				if (min > input[j]) {
					min = input[j];
					targetIndex = j;
				}
			}
			int temp = input[i];
			input[i] = min;
			input[targetIndex] = temp;
		}
	}

	private static void insertionSort(int N) {
		for (int i = 1; i < N; i++) {
			int insertionValue = input[i];
			for (int j = i - 1; j >= 0; j--) {
				if (insertionValue > input[j]) {
					break;
				} else {
					input[j + 1] = input[j];
					input[j] = insertionValue;
				}
			}
		}
	}

	private static void quickSort(int N, int low, int high, int depth) {
		for (int i = 0; i < depth; i++) {
			System.err.print("    ");
		}
		System.err.print("[" + low + ", " + high + "] : [");
		if (low >= high) {
			System.err.println("]");
			return;
		}

		int pivot = input[low];
		int i = low;
		int k = high + 1;
		do {
			do {
				++i;
			} while (i < k && pivot > input[i]);
			do {
				--k;
			} while (pivot < input[k]);
			if (i < k) {
				int temp = input[i];
				input[i] = input[k];
				input[k] = temp;
			}
		} while (i < k);

		input[low] = input[k];
		input[k] = pivot;

		for (i = 0; i < input.length; i++) {
			System.err.print(input[i] + ", ");
		}
		System.err.println("]");

		quickSort(N, low, k - 1, depth + 1);
		quickSort(N, k + 1, high, depth + 1);
	}

}
