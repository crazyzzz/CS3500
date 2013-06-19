JFLAGS = -g
JDEBUG = -Xlint
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Bunco.java \
	TestBunco.java \
	ThreeDice.java

default: classes

classes: $(CLASSES:.java=.class)


clean: 
	$(RM) *.class

test:
	java TestBunco
