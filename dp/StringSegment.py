class StringSegment:
	def average(self, s):
		start = 0
		sum = 0
		count = 0
		while start < len(s):
			end = start
			while end + 1 < len(s) and s[end+1] == s[start]:
				end += 1
			n = end - start + 1
			count += 1
			sum += n
			start = end + 1
		return sum * 1.0 / count
