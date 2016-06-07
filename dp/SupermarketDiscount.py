class SupermarketDiscount:
	def minAmount(self, goods):
		min_cost = self.buy(goods)
		min_cost = min(min_cost, self.buy([sum(goods)]))
		for i in range(0, 3):
			for j in range(i+1, 3):
				min_cost = min(min_cost, self.buy([goods[i] + goods[j], goods[3-i-j]]))
		return min_cost
	def buy(self, goods):
		cost = 0
		for good in goods:
			if good >= 50:
				cost += good - 10
			else:
				cost += good
		return cost
