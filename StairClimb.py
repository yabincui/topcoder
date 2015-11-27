class StairClimb:
	def stridesTaken(self, flights, stairsPerStride):
		steps = 0
		for i in range(len(flights)):
			steps += flights[i] / stairsPerStride
			if flights[i] % stairsPerStride != 0:
				steps += 1
			if i + 1 < len(flights):
				steps += 2
		return steps
