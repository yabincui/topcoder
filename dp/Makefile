# TODO: support writing c++ code.

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
                TaroCardsTest MiningGoldEasyTest VocaloidsAndSongsTest CombinationLockDiv2Test \
                AlienAndSetDiv2Test FoxConnection2Test WinterAndReindeersTest SimilarNames2Test \
                LittleElephantAndSubsetTest LittleElephantAndXorTest MayTheBestPetWinTest \
                LittleElephantAndArrayTest YetAnotherTwoTeamsProblemTest FoxAndShogiTest \
                FlippingBitDiv2Test GameInDarknessDiv2Test StringWeightDiv2Test Excavations2Test \
                WallGameDiv2Test WolfInZooDivTwoTest EllysCoprimesDiv2Test WolfPackDivTwoTest \
                DistinctRemaindersTest MegaFactorialDiv2Test ShuffleSortTest MountainEasyTest \
                FencingPenguinsEasyTest SpellCardsEasyTest RandomOptionTest FoxAndTouristFamiliesTest \
                FoxAndMountainTest LeftRightDigitsGameTest MuddyRoad2Test TheBrickTowerHardDivTwoTest \
                SafeRemovalTest ColorfulCupcakesDivTwoTest OrderOfTheHatsTest KingdomAndPasswordTest \
                RelativelyPrimeSubsetTest AliceBobShuffleTest FoxAndSortingTest \
                EllysFiveFriendsTest MagicalGirlTest DengklekPaintingSquaresTest MagicNamingTest \
                SmallBricks31Test SRMSystemTestPhaseTest CoinReversingTest CuttingGrassTest \
                FiveHandredElevenTest YetAnotherORProblem2Test TheTicketsDivTwoTest \
                TheCowDivTwoTest FoxAverageSequenceTest MakeSquareTest AlternatingLaneTest \
                SolitaireChessTest GrafixMaskTest MarketingTest CircuitsTest PathFindingTest \
                EscapeTest KiloManXTest RoboCourierTest IslandFerriesTest DungeonEscapeTest \
                BombManTest TeamBuilderTest RookAttackTest GraduationTest ParkingTest \
                PlayingCubesTest CheeseRollingTest SolitaireChessTest RandomAppleEasyTest CarelessSecretaryTest \
                SubAnagramsTest SquaresCoveringTest RectangularIslandTest GiftsTest WeirdTimesTest \
                ColorfulMazeTwoTest NameInputTest ParkAmusementTest DonutsOnTheGridTest \
                NumbersAndMatchesTest HexagonalBattlefieldEasyTest TheCardLineDivTwoTest \
                CollectingTokensTest ShopPositionsTest RotatingTrianglesTest Polygons2Test EqualTowersTest \
                WickedTeacherTest PalindromeFactoryTest BirdsCountingTest ImageTradersTest TheDictionaryTest \
                TheEasyChaseTest BarracksEasyTest DancingCouplesTest InfiniteSequenceTest \
                ClosestRegexTest InfiniteSoupTest MarblesInABagTest CheapestRouteTest PolygonColorsTest \
                GetToTheTopTest TheSumOfLuckyNumbersTest RandomSortTest CollectingMarblesTest TriviaGameTest \
                PowerAdaptersTest MarbleCollectionGameTest SetOfPatternsTest MarblesRegroupingHardTest \
                LittleTreeTest PowerGameTest CharmingTicketsEasyTest JohnnysPhoneTest IsoscelesTriangulationsTest \
                PowerPlantsTest CrazyComponentsTest FixedSizeSumsTest BearDartsTest VampireTreeDiv2Test \

all: $(lastword $(UnitTestList))

test_all: $(UnitTestList)


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

%Test: %.class %Test.class
	java $(JUNIT_MAIN) $@

%Test: %Test.py
	python $<

clean:
	rm -rf *.class
