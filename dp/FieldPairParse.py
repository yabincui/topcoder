class FieldPairParse:
	def getPairs(self, data):
		inSpace = False
		rows = len(data)
		cols = len(data[0])
		result = []
		for col in range(cols):
			isSpace = True
			for row in range(rows):
				if data[row][col] == 'X':
					isSpace = False
					break
			if not isSpace:
				inSpace = False
			else:
				if inSpace:
					result[-1] += 1
				else:
					inSpace = True
					result.append(1)
		if len(result) % 2 == 0:
			return []
		return result
