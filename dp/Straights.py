class Straights:
	def howMany(self, hand, k):
		sum = 0
		for i in range(0, len(hand)-k+1):
			count = 1
			for j in range(0, k):
				count *= hand[i+j]
			sum += count
		return sum
