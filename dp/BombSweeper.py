class BombSweeper:
	def winPercentage(self, board):
		win = 0
		losses = 0
		rows = len(board)
		cols = len(board[0])
		for r in range(rows):
			for c in range(cols):
				if board[r][c] == 'B':
					losses += 1
					continue
				nearBomb = False
				for dr in range(-1, 2, 1):
					for dc in range(-1, 2, 1):
						nr = dr + r
						nc = dc + c
						if nr < 0 or nr >= rows or nc < 0 or nc >= cols or (r == nr and c == nc):
							continue
						if board[nr][nc] == 'B':
							nearBomb = True
							break
					if nearBomb:
						break
				if not nearBomb:
					win += 1
		return win * 100.0 / (win + losses)
						
