#!/bin/bash
# export SCRATCHDIR=`mktemp -d`
{
    ./runtm "${TESTDIR}/simple.tm"
    export RESULT=$?
if [[ x$RESULT != x0 ]]; then exit 1; fi
} |
grep -ve '^[[:space:]]' |
diff -w - "${TESTDIR}/notape.tmout"
export RESULT=$?
rm -rf $SCRATCHDIR
exit $RESULT
