/*
 * 회문 (Palindrome) 여부 판별
 */

public class Palindrome {
	private static final String input1 = "eyye";
	private static final String input2 = "기러기";
	private static final String input3 = "hello";

	public static void main(String[] args) {
		System.out.println(input1 + " : " + isPalindrome(input1));
		System.out.println(input2 + " : " + isPalindrome(input2));
		System.out.println(input3 + " : " + isPalindrome(input3));
	}

	private static boolean isPalindrome(String input) {
		if (input == null) {
			return false;
		}

		int length = input.length();
		int midIndex = length / 2;
		int lastIndex = length - 1;
		for (int i = 0; i < midIndex; i++) {
			if (input.charAt(i) != input.charAt(lastIndex - i)) {
				return false;
			}
		}

		return true;
	}

}
