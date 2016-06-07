class BritishCoins:
	def coins(self, pence):
		penny = pence % 12
		shilling = pence / 12
		pound = shilling / 20
		shilling %= 20
		return [pound, shilling, penny]
