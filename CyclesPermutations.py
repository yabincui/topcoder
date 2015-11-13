class CyclesInPermutations:
	def maxCycle(self, board):
		dp = [0 for x in board]
		for i in range(len(board)):
			if dp[i] != 0:
				continue
			start = i
			cur = board[start] - 1
			count = 1
			while cur != start:
				count += 1
				cur = board[cur] - 1
			dp[start] = count
			cur = board[start] - 1
			while cur != start:
				dp[cur] = count
				cur = board[cur] - 1
		return max(dp)
			
