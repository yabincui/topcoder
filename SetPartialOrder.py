class SetPartialOrder:
	def compareSets(self, a, b):
		a_dict = {}
		b_dict = {}
		for v in a:
			a_dict[v] = True
		for v in b:
			b_dict[v] = True
		a_less_equal_b = True
		for v in a:
			if not b_dict.has_key(v):
				a_less_equal_b = False
				break
		b_less_equal_a = True
		for v in b:
			if not a_dict.has_key(v):
				b_less_equal_a = False
		if a_less_equal_b and b_less_equal_a:
			return 'EQUAL'
		elif a_less_equal_b and not b_less_equal_a:
			return 'LESS'
		elif not a_less_equal_b and b_less_equal_a:
			return 'GREATER'
		else:
			return 'INCOMPARABLE'
