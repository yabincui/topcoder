import math

class BestTriangulation:
	def maxArea(self, vertices):
		result = 0
		for i in range(len(vertices)):
			for j in range(i+1, len(vertices)):
				for k in range(j+1, len(vertices)):
					strs = vertices[i].split(' ')
					p1 = (int(strs[0]), int(strs[1]))
					strs = vertices[j].split(' ')
					p2 = (int(strs[0]), int(strs[1]))
					strs = vertices[k].split(' ')
					p3 = (int(strs[0]), int(strs[1]))
					a = self.dist(p1, p2)
					b = self.dist(p1, p3)
					c = self.dist(p2, p3)
					p = (a + b + c) / 2
					area = math.sqrt(p * (p - a) * (p - b) * (p - c))
					result = max(result, area)
		return result
	
	def dist(self, p1, p2):
		dx = abs(p1[0] - p2[0])
		dy = abs(p1[1] - p2[1])
		return math.sqrt(dx * dx + dy * dy)
