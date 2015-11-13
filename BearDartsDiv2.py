import unittest

class BearDartsDiv2:
  def count(self, w):
    w = list(w)
    n = len(w)
    d = {}
    for value in w:
      d[value] = d.get(value, 0) + 1
    result = 0
    mul_dict = {}
    for i in range(0, n):
      d[w[i]] -= 1
      for key in mul_dict.keys():
        mul = key * w[i]
        result += d.get(mul, 0) * mul_dict[key]
      for j in range(0, i):
        mul = w[i] * w[j]
        mul_dict[mul] = mul_dict.get(mul, 0) + 1
    return result


values = [724068, 215614, 492317, 530669, 619058, 642439, 464128, 717055, 172888, 561346, 882650, 965600, 929038, 391263, 316397, 107004, 565041, 732824, 444912, 163463, 475661, 863040, 97913, 440714, 161830, 113, 205663, 82467, 98329, 783971, 901930, 822397, 999585, 394246, 869417, 134994, 553037, 333544, 368400, 725924, 411242, 251050, 207876, 340279, 158664, 524272, 963634, 723705, 257095, 408546, 887167, 249107, 271585, 501431, 206172, 433414, 501543, 411834, 32233, 116224, 712157, 934162, 454972, 228093, 844760, 840740, 363086, 914148, 690635, 731486, 156423, 101876, 498887, 364298, 958506, 657550, 888569, 922140, 897606, 662015, 330685, 784772, 427474, 118621, 286202, 633645, 68387, 304097, 45479, 100619, 936672, 757635, 551132, 907995, 502079, 912243, 748734, 381516, 342742, 439368, 113001, 499165, 57596, 128239, 863462, 16101, 302141, 268383, 454592, 199746, 446749, 301628, 500870, 874222, 936601, 787071, 507867, 4987, 607519, 553345, 621957, 60542, 827331, 689440, 968536, 845761, 601683, 717269, 227276, 944424, 156637, 340277, 959940, 730584, 984867, 339754, 746684, 287007, 608136, 717628, 3105, 571236, 535607, 503974, 445458, 472207, 807396, 953324, 477193, 414915, 23020, 615501, 475456, 366702, 304941, 960344, 212462, 422975, 677612, 439737, 883750, 350600, 296365, 843690, 81183, 797584, 183443, 344219, 600942, 307930, 61846, 604046, 879165, 597452, 108019, 840974, 586011, 915415, 794297, 63203, 846681, 333668, 195056, 838488, 216721, 499996, 798831, 945534, 439322, 992795, 385271, 323071, 343394, 197987, 683112, 424577, 511922, 866554, 768795, 112864, 690835, 346992, 233261, 86352, 944443, 341280, 927325, 530453, 773046, 237974, 110008, 136078, 87993, 821415, 490917, 304714, 321410, 806100, 250247, 760731, 798894, 151869, 600153, 142287, 866208, 283265, 566863, 378129, 666170, 852009, 7344, 873357, 199000, 240605, 959708, 659795, 581884, 403384, 706599, 871281, 157709, 332958, 523710, 245702, 154372, 14626, 66767, 475781, 337077, 317013, 752863, 135970, 985234, 353016, 794609, 851441, 152632, 877823, 745921, 818801, 729832, 753265, 692157, 928831, 993869, 168216, 104977, 92104, 571600, 811576, 479736, 729308, 144533, 3445, 491361, 298905, 534422, 558127, 291037, 871499, 391492, 43900, 523820, 893077, 913267, 318428, 744517, 582250, 196251, 490437, 401050, 926082, 760053, 609559, 371264, 753921, 777774, 476241, 846024, 865725, 287816, 325759, 111385, 948700, 845555, 602745, 247604, 896329, 677224, 54993, 767827, 585067, 615244, 807998, 478143, 528510, 126426, 739011, 110759, 839028, 229447, 28160, 765109, 505852, 637718, 652724, 259772, 415492, 645316, 622148, 797568, 933131, 464258]

class BearDartsDiv2Test(unittest.TestCase):
  def test1(self):
    bear = BearDartsDiv2()
    result = bear.count(values)
    print 'result = %d' % result

unittest.main()