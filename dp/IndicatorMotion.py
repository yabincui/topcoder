class IndicatorMotion:
	def getMotion(self, program, startState):
		print 'program = %s, startState = %s' % (program, startState)
		return '\\'
		result = []
		cur = startState
	
		result.append(cur)
		i = 0
		while i < len(program):
			action = program[i]
			i += 1
			sec = 0
			while i < len(program) and program[i] >= '0' and program[i] <= '9':
				sec = sec * 10 + int(program[i])
				i += 1
			while sec != 0:
				sec -= 1
				print 'cur = %s, action = %s' % (cur, action)
				cur = self.move(cur, action)
				result.append(cur)
		print 'return %s' % ''.join(result)
		return ''.join(result)
	
	def move(self, cur, action):
		if action == 'L':
			if cur == '|':
				return '\\'
			elif cur == '\\':
				return '-'
			elif cur == '-':
				return '/'
			elif cur == '/':
				return '|'
		elif action == 'R':
			if cur == '\\':
				return '|'
			elif cur == '|':
				return '/'
			elif cur == '/':
				return '-'
			elif cur == '-':
				return '\\'
		elif action == 'S':
			return cur
		elif action == 'F':
			if cur == '\\':
				return  '/'
			elif cur == '/':
				return '\\'
			elif cur == '-':
				return '|'
			elif cur == '|':
				return '-'
	

if __name__ == '__main__':
  ind = IndicatorMotion()
  result = ind.getMotion('F00R00L00S00', '\\')
  print 'result = %s' % result	
