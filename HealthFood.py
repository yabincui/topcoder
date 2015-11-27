class HealthFood:
	def selectMeals(self, protein, carbs, fat, dietPlans):
		calories = []
		for i in range(len(protein)):
			c = protein[i] * 5 + carbs[i] * 5 + fat[i] * 9
			calories.append(c)
		result = []
		for plan in dietPlans:
			best = 0
			for i in range(1, len(protein)):
				better = 0
				for c in plan:
					if c == 'C':
						better = carbs[i] - carbs[best]
					elif c == 'c':
						better = carbs[best] - carbs[i]
					elif c == 'P':
						better = protein[i] - protein[best]
					elif c == 'p':
						better = protein[best] - protein[i]
					elif c == 'F':
						better = fat[i] - fat[best]
					elif c == 'f':
						better = fat[best] - fat[i]
					elif c == 'T':
						better = calories[i] - calories[best]
					elif c == 't':
						better = calories[best] - calories[i]
					if better != 0:
						break
				if better > 0:
					best = i
			result.append(best)
		return result
					
