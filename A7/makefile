TESTS=$(wildcard su*)

all: $(TESTS)
.PHONY: force
$(TESTS): force
	cd $@ && make
test: 
	./testsummarize.pl
