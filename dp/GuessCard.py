class GuessCard:
	def whichRow(self, width, height, columns):
		board = []
		for i in range(height):
			c = []
			for j in range(width):
				c.append(i * width + j)
			board.append(c)
		possible = {}
		for i in range(height):
			possible[board[i][columns[0]]] = i
		for round in range(1, len(columns)):
			board = self.rearrange(board, width, height)
			nextPossible = {}
			for i in range(height):
				key = board[i][columns[round]]
				if key in possible:
					nextPossible[key] = i
			possible = nextPossible
		keys = possible.keys()
		if len(keys) == 1:
			return possible[keys[0]]
		return -1
	
	def rearrange(self, board, width, height):
		curRow = 0
		curCol = 0
		nextBoard = []
		for i in range(height):
			c = []
			for j in range(width):
				value = board[curRow][curCol]
				curRow += 1
				if curRow == height:
					curRow = 0
					curCol += 1
				c.append(value)
			nextBoard.append(c)
		return nextBoard
	
