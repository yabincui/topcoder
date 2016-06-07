class MedalTable:
	class Country:
		def __init__(self, name):
			self.name = name
			self.gold = 0
			self.silver = 0
			self.bronze = 0
		
		def __lt__(self, other):
			if self.gold != other.gold:
				return self.gold > other.gold
			if self.silver != other.silver:
				return self.silver > other.silver
			if self.bronze != other.bronze:
				return self.bronze > other.bronze
			return self.name < other.name
			
	def generate(self, results):
		countryDict = {}
		for result in results:
			strs = result.split(' ')
			for s in strs:
				if s not in countryDict:
					countryDict[s] = self.Country(s)
			countryDict[strs[0]].gold += 1
			countryDict[strs[1]].silver += 1
			countryDict[strs[2]].bronze += 1
		countries = countryDict.values()
		countries.sort()
		result = []
		for country in countries:
			result.append('%s %d %d %d' % (country.name, country.gold, country.silver, country.bronze))
		return result
