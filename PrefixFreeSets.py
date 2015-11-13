class PrefixFreeSets:
	class Node:
		def __init__(self):
			self.children = {}
		def findOrCreateChild(self, ch):
			if not self.children.has_key(ch):
				self.children[ch] = PrefixFreeSets.Node()
			return self.children[ch]
		def getLeafCount(self):
			if len(self.children) == 0:
				return 1
			sum = 0
			for child in self.children.items():
				sum += child[1].getLeafCount()
			return sum
				
	def maxElements(self, words):
		root = self.Node()
		for word in words:
			cur = root
			for ch in word:
				cur = cur.findOrCreateChild(ch)
		return root.getLeafCount()
