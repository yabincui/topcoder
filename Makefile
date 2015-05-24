include gmsl-1.1.7/gmsl

UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest AvoidRoadsTest ChessMetricTest \
								JewelryTest StripePainterTest QuickSumsTest ShortPalindromesTest StarAdventureTest \
								MiniPaintTest FibonacciDiv2Test SRMCardsTest ShorterSuperSumTest ThePalindromeTest \
								MutaliskEasyTest RandomPancakeStackDiv2Test NumbersChallengeTest ChooseTheBestOneTest \
								EmoticonsDiv2Test PalindromicSubstringsDiv2Test ColorfulRoadTest \
								AstronomicalRecordsEasyTest TrafficCongestionDivTwoTest StampTest \
								CuttingBitStringTest PillarsDivTwoTest StrIIRecTest EvenRouteTest \
								EllysCheckersTest NoRepeatPlaylistTest P8XGraphBuilderTest FoxPlayingGameTest \
								RouteIntersectionTest PrimeSoccerTest SentenceDecompositionTest \
								ChangingSoundsTest HandsShakingTest NumberofFiboCallsTest ProblemsToSolveTest \
								RGBStreetTest MatchNumbersEasyTest NineEasyTest BoardFoldingDiv2Test \
                GoodSubsetTest TaroCoinsTest BubbleSortWithReversalsTest GameOfSegmentsTest \
                RandomGraphTest MovingRooksDiv2Test TwoLLogoTest MergeStringsTest \
                TaroCardsTest MiningGoldEasyTest VocaloidsAndSongsTest \

all: $(call last,$(UnitTestList))

test_all: $(UnitTestList)


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

%Test: %.class %Test.class
	java $(JUNIT_MAIN) $@

clean:
	rm -rf *.class
