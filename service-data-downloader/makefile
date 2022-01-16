# http://blog.z3bra.org/2013/09/java-without-eclipse.html

class_d=run
lib_d=lib
source_d=src

JAVAC=javac
JFLAGS= -g -d $(class_d) -sourcepath $(source_d) -Xlint:all

classpath:=$(class_d):$(lib_d)/algs4.jar:$(lib_d)/twitter4j-core-4.0.7.jar

export CLASSPATH:=$(classpath)

S_PARSER= $(shell find $(source_d) -regex '.*\.java')
C_PARSER= $(patsubst src/%.java, run/%.class, $(S_PARSER))

.SUFFIXES: .java .class
.PHONY: default server client utils clean init all

default: classes

$(class_d)/%.class: $(source_d)/%.java
	@echo "JAVAC $<"
	@$(JAVAC) $(JFLAGS) $<

classes: $(lib_d) algs4.jar twitter4j-4.0.7.zip $(class_d) $(C_PARSER)

$(lib_d):
	mkdir -p $(lib_d)

$(class_d):
	mkdir -p $(class_d)

algs4.jar:
	wget -N https://algs4.cs.princeton.edu/code/algs4.jar
	touch $@
	cp $@ ./lib/$@

twitter4j-4.0.7.zip:
	wget -N http://twitter4j.org/archive/twitter4j-4.0.7.zip
	touch $@
	unzip twitter4j-4.0.7.zip