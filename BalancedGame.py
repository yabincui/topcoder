import math

class BalancedGame:
	def result(self, conflicts, p, q):
		n = len(conflicts)
		neededWin = math.ceil((n - 1) * p / 100.0)
		neededLose = math.ceil((n - 1) * q / 100.0)
		for i in range(len(conflicts)):
			row = conflicts[i]
			win = sum([1 if x == 'W' else 0 for x in row])
			lose = sum([1 if x == 'L' else 0 for x in row])
			if win < neededWin or lose < neededLose:
				return i
		return -1
