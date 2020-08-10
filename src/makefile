JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Machine.java \
	Description.java \
	TM.java 

default: classes runtm

classes: $(CLASSES:.java=.class)

runtm:
	@echo "#!/bin/bash" > runtm 
	@echo "java TM \$$1 \$$2" >> runtm 
	chmod u+x runtm

clean:
	$(RM) *.class runtm
