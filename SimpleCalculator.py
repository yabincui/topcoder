import re

class SimpleCalculator:
	def calculate(self, input):
		m = re.search(r'^(\d+)([-+*/])(\d+)$', input)
		a = int(m.group(1))
		op = m.group(2)
		b = int(m.group(3))
		if op == '+':
			return a + b
		if op == '-':
			return a - b
		if op == '*':
			return a * b
		if op == '/':
			return a / b
