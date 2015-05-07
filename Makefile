UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest AvoidRoadsTest ChessMetricTest \
								JewelryTest StripePainterTest QuickSumsTest ShortPalindromesTest StarAdventureTest \

all: StarAdventureTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

%Test: %.class %Test.class
	java $(JUNIT_MAIN) $@

clean:
	rm -rf *.class
