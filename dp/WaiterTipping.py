class WaiterTipping:
	def maxPercent(self, total, taxPercent, money):
		sub = money - total - total * taxPercent / 100
		if sub < 0:
			return -1
		start = sub * 100 / total
		end = (sub * 100 + 99) / total
		return end
