UnitTestList := ZigZagTest BadNeighborsTest FlowerGardenTest AvoidRoadsTest ChessMetricTest \
								JewelryTest StripePainterTest QuickSumsTest ShortPalindromesTest StarAdventureTest \
								MiniPaintTest \

all: MiniPaintTest


JUNIT_MAIN = org.junit.runner.JUnitCore

%.class : %.java
	javac $<

%Test: %.class %Test.class
	java $(JUNIT_MAIN) $@

clean:
	rm -rf *.class
