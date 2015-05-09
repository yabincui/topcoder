UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest AvoidRoadsTest ChessMetricTest \
								JewelryTest StripePainterTest QuickSumsTest ShortPalindromesTest StarAdventureTest \
								MiniPaintTest FibonacciDiv2Test SRMCardsTest ShorterSuperSumTest ThePalindromeTest \
								MutaliskEasyTest RandomPancakeStackDiv2Test NumbersChallengeTest ChooseTheBestOneTest \
								EmoticonsDiv2Test PalindromicSubstringsDiv2Test \

all: PalindromicSubstringsDiv2Test


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

%Test: %.class %Test.class
	java $(JUNIT_MAIN) $@

clean:
	rm -rf *.class
