class RelationClassifier:
	def isBijection(self, domain, range):
		d = {}
		for x in domain:
			if x in d:
				return 'Not'
			d[x] = True
		d = {}
		for x in range:
			if x in d:
				return 'Not'
			d[x] = True
		return 'Bijection'
