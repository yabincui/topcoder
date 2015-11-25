class KDoubleSubstrings:
	def howMuch(self, str, k):
		count = 0
		str = ''.join(str)
		for start in range(len(str)):
			for n in range(1, (len(str) - start)/2 + 1):
				mid = start + n
				diff = 0
				for i in range(n):
					if str[start+i] != str[mid+i]:
						diff += 1
				if diff <= k:
					count += 1
		return count
