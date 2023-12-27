# Adapted from https://github.com/thockin/go-build-template released under the
# Apache-2.0 license.

DBG_MAKEFILE ?=
ifeq ($(DBG_MAKEFILE),1)
    $(warning ***** starting Makefile for goal(s) "$(MAKECMDGOALS)")
    $(warning ***** $(shell date))
else
    # If we're not debugging the Makefile, don't echo recipes.
    MAKEFLAGS += -s
endif

test: # @HELP test all rules with Semgrep
test:
	./scripts/test.sh

watch: # @HELP watch for file changes and auto-run affected tests
watch:
	./scripts/watch.sh

format: # @HELP format rule files according to guidelines
format:
	./ci/format.rb

help: # @HELP prints this message
help:
	echo "TARGETS:"
	grep -E '^.*: *# *@HELP' $(MAKEFILE_LIST)     \
	    | awk '                                   \
	        BEGIN {FS = ": *# *@HELP"};           \
	        { printf "  %-20s %s\n", $$1, $$2 };  \
	    '
