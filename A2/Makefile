JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	FListInteger.java 

default: classes

classes: $(CLASSES:.java=.class)

clean: 
	$(RM) *.class

test:
	java TestFSet
