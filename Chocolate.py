import math

class Chocolate:
	def minSplitNumber(self, width, height, nTiles):
		if width * height == nTiles:
			return 0
		if nTiles > width * height:
			return -1
		if nTiles % width == 0 or nTiles % height == 0:
			return 1
		result = self.getFactor(min(width,height), max(width, height), nTiles)
		return 2 if result else -1
	
	def getFactor(self, a, b, nTiles):
		if a >= nTiles or b >= nTiles:
			return True
		
		start = max(2, nTiles / b + 1)
		end = min(a, int(math.sqrt(nTiles)))

		x = start
		while x <= end:
			if nTiles % x == 0:
				print nTiles, x
				return True
			x += 1 
		return False
		
