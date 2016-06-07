class GardenHose:
	def countDead(self, n, rowDist, colDist, hostDist):
		waterInRow = hostDist / rowDist
		waterInCol = hostDist / colDist
		if waterInRow * 2 >= n or waterInCol * 2 >= n:
			return 0
		lastCol = n - waterInRow * 2
		lastRow = n - waterInCol * 2
		return lastCol * lastRow
