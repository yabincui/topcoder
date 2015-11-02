class HuffmanDecoding:
    def decode(self, archive, dictionary):
        bitDict = {}
        for i in range(len(dictionary)):
            bitDict[dictionary[i]] = chr(i + ord('A'))
        result = []
        cur = 0
        while cur < len(archive):
            for end in range(cur+1, len(archive)+1):
                if bitDict.has_key(archive[cur:end]):
                    result.append(bitDict[archive[cur:end]])
                    cur = end
                    break
        return ''.join(result)