class StreetParking:
	def freeParks(self, street):
		count = 0
		for i in range(len(street)):
			x = street[i]
			if x == 'D':
				continue
			if x == 'B':
				continue
			if i + 1 < len(street) and street[i+1] == 'B':
				continue
			if i + 2 < len(street) and street[i+2] == 'B':
				continue
			if x == 'S':
				continue
			if i + 1 < len(street) and street[i+1] == 'S':
				continue
			if i > 0 and street[i-1] == 'S':
				continue
			count += 1
		return count
