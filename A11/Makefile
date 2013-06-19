JFLAGS = -g
JDEBUG = -Xlint
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Bunco.java \
	TestBunco.java \
	ThreeDice.java \
	ScoreBoard.java

default: classes

classes: $(CLASSES:.java=.class)


clean: 
	$(RM) *.class && $(RM) *~

test:
	java TestBunco
