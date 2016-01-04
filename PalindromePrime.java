public class PalindromePrime {
	public int count(int L, int R) {
		int result = 0;
		for (int i = L; i <= R; ++i) {
			if (isPrime(i) && isPalindrome(i)) {
				result++;
			}
		}
		return result;
	}
	
	boolean isPrime(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i <= n/2; ++i) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	boolean isPalindrome(int n) {
		String s = Integer.valueOf(n).toString();
		for (int l = 0, r = s.length() - 1; l < r; ++l, --r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
		}
		return true;
	}
}
