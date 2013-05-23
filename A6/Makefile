JFLAGS = -g
JDEBUG = -Xlint
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	FMap.java \
    TestFMap.java

default: classes

classes: $(CLASSES:.java=.class)


clean: 
	$(RM) *.class

test:
	java TestFMap

