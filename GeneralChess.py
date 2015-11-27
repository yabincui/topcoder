class GeneralChess:
	class Point:
		def __init__(self, x, y):
			self.x = x
			self.y = y
		
		def __lt__(self, other):
			if self.x != other.x:
				return self.x < other.x
			return self.y < other.y
		
		def __eq__(self, other):
			return self.x == other.x and self.y == other.y
		
		def __ne__(self, other):
			return not self.__eq__(other)
	
	def attackPositions(self, pieces):
		result = []
		points = []
		for piece in pieces:
			strs = piece.split(',')
			points.append(self.Point(int(strs[0]), int(strs[1])))
		for point in points:
			for dx in [-2, -1, 1, 2]:
				for dy in [-2, -1, 1, 2]:
					if abs(dx) == abs(dy):
						continue
					x = point.x + dx
					y = point.y + dy
					if self.canThreat(x, y, points):
						result.append(self.Point(x, y))
		result.sort()
		ret = []
		for i in range(len(result)):
			if i == 0 or result[i-1] != result[i]:
				ret.append('%d,%d' % (result[i].x, result[i].y))
		return ret
	
	def canThreat(self, x, y, points):
		for point in points:
			dx = abs(x - point.x)
			dy = abs(y - point.y)
			if dx == 1 and dy == 2:
				continue
			if dx == 2 and dy == 1:
				continue
			return False
		return True
