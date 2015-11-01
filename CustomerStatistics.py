class CustomerStatistics:
    class Node:
        def __init__(self, name):
            self.name = name
            self.count = 1
        
        def __lt__(self, other):
            return self.name < other.name
        
        
    def reportDuplicates(self, customerNames):
        nodes = []
        for name in customerNames:
            found = False
            for node in nodes:
                if node.name == name:
                    node.count += 1
                    found = True
                    break
            if not found:
                nodes.append(self.Node(name))
        nodes.sort()
        result = []
        for node in nodes:
            if node.count > 1:
                result.append('%s %d' % (node.name, node.count))
        print result
        return tuple(result)