class SubstitutionCipher:
	def decode(self, a, b, y):
		from_dict = {}
		to_dict = {}
		map = {}
		for i in range(26):
			from_dict[chr(i + ord('A'))] = True
			to_dict[chr(i + ord('A'))] = True
		for i in range(len(a)):
			if not map.has_key(a[i]):
				map[a[i]] = b[i]
				delete from_dict[a[i]]
				delete to_dict[b[i]]
		if len(from_dict.keys()) == 1:
			map[from_dict.keys()[0]] = to_dict.keys()[0]
		reverse_map = {}
		for (k,v) in map:
			reverse_map[v] = k
		x = ''
		for v in y:
			if not reverse_map.has_key(v):
				return ''
			x += reverse_map[v]
		return x
