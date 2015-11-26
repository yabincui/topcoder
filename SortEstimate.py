import math

class SortEstimate:
	def howMany(self, c, time):
		low = 1
		high = max(time, 2)
		iteration = 0
		while True:
			diff = high - low
			if diff < 1e-9 or diff / low < 1e-9:
				break
			mid = (low + high) / 2.0
			value = c * mid * math.log(mid, 2)
			if value <= time:
				low = mid
			else:
				high = mid
		return (low + high) / 2.0

if __name__ == '__main__':
	sortEstimate = SortEstimate()
	value = sortEstimate.howMany(100, 1)
	print 'value = %.10f' % value
