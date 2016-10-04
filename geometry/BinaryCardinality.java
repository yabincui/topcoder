import java.util.*;

public class BinaryCardinality {
	
	class Num {
		Num(int n) {
			number = n;
			binary = 0;
			while (n != 0) {
				if ((n & 1) != 0) {
					binary++;
				}
				n >>= 1;
			}
		}
		
		int number;
		int binary;
	}
	
	class NumComparator implements Comparator<Num> {
		public int compare(Num n1, Num n2) {
			if (n1.binary != n2.binary) {
				return n1.binary - n2.binary;
			}
			return n1.number - n2.number;
		}
	}
	
	public int[] arrange(int[] numbers) {
		Num[] nums = new Num[numbers.length];
		for (int i = 0; i < numbers.length; ++i) {
			nums[i] = new Num(numbers[i]);
		}
		Arrays.sort(nums, new NumComparator());
		int[] result = new int[numbers.length];
		for (int i = 0; i < nums.length; ++i) {
			result[i] = nums[i].number;
			//System.out.printf("i = %d, number = %d, binary = %d\n", i, nums[i].number, nums[i].binary);
		}		
		return result;
	}
}
