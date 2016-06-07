import re

class ClientsList:
	class  Node:
		def __init__(self, first, last):
			self.first = first
			self.last = last
		
		def __lt__(self, other):
			if self.last != other.last:
				return self.last < other.last
			return self.first < other.first
	
	def dataCleanup(self, names):
		nodes = []
		for name in names:
			if ',' in name:
				m = re.search(r'^(\S+), (\S+)$', name)
				nodes.append(self.Node(m.group(2), m.group(1)))
			else:
				m = re.search(r'^(\S+) (\S+)$', name)
				nodes.append(self.Node(m.group(1), m.group(2)))
		nodes.sort()
		result = []
		for node in nodes:
			result.append('%s %s' % (node.first, node.last))
		return result
