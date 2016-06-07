class MovingAverages:
	def calculate(self, times, n):
		values = []
		for i in range(len(times)):
			strs = times[i].split(':')
			t = ((int(strs[0]) * 60) + int(strs[1])) * 60 + int(strs[2])
			values.append(t)
		result = []
		curSum = 0
		for i in range(len(values)):
			curSum += values[i]
			if i > n - 1:
				curSum -= values[i-n]
			if i >= n - 1:
				result.append(curSum / n)
		return result
