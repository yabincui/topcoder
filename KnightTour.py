class KnightTour:
	def checkTour(self, cells):
		self.board = [[False for x in range(6)] for x in range(6)]
		if not self.mark(cells[0]):
			return "Invalid"
		for i in range(1, len(cells)):
			if not self.connected(cells[i-1], cells[i]):
				return 'Invalid'
			if not self.mark(cells[i]):
				return 'Invalid'
		if not self.connected(cells[-1], cells[0]):
			return 'Invalid'
		return 'Valid'
	def mark(self, cell):
		(row, col) = self.getPos(cell)
		if self.board[row][col]:
			return False
		self.board[row][col] = True
		return True
	def connected(self, cellOne, cellTwo):
		(row1, col1) = self.getPos(cellOne)
		(row2, col2) = self.getPos(cellTwo)
		diffRow = abs(row1 - row2)
		diffCol = abs(col1 - col2)
		if diffRow == 1 and diffCol == 2:
			return True
		if diffRow == 2 and diffCol == 1:
			return True
		return False
	def getPos(self, cell):
		return (6 - int(cell[1]), ord(cell[0]) - ord('A'))
