class RailroadSeatNumeration:
	def getInternational(self, tickets):
		matchDomestic = self.matchUsingDomestic(tickets)
		matchInternational = self.matchUsingInternational(tickets)
		if matchDomestic and not matchInternational:
			result = self.toInternational(tickets)
		elif matchDomestic and matchInternational:
			return 'AMBIGUOUS'
		elif not matchDomestic and matchInternational:
			result = tickets
		else:
			return 'BAD DATA'
		return ' '.join([str(x) for x in result])
	
	def matchUsingDomestic(self, tickets):
		for x in tickets:
			if x >= 1 and x <= 36:
				continue
			return False
		return True
	
	def matchUsingInternational(self, tickets):
		for x in tickets:
			d1 = x / 10
			d2 = x % 10
			if d1 >= 1 and d1 <= 9 and d2 in [1, 3, 4, 6]:
				continue
			return False
		return True
	
	def toInternational(self, tickets):
		result = []
		for x in tickets:
			d1 = (x - 1) / 4 + 1
			d2 = [1, 3, 4, 6][(x - 1) % 4]
			result.append(d1 * 10 + d2)
		return result
			
