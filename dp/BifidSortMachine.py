class BifidSortMachine:
	def countMoves(self, a):
		a = list(a)
		sorted_a = sorted(a)
		min_change = len(a)
		for mid_start in range(0, len(a)):
			for mid_end in range(len(a)-1, mid_start-1, -1):
				cur = mid_start
				valid = True
				for i in range(len(a)):
					if a[i] < sorted_a[mid_start] or a[i] > sorted_a[mid_end]:
						continue
					if a[i] != sorted_a[cur]:
						valid = False
						break
					else:
						cur += 1
				if valid:
					min_change = min(min_change, len(a) - (mid_end - mid_start + 1))
					break
		return min_change
				
		
