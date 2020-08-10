#!/bin/bash
#export SCRATCHDIR=`mktemp -d`
{
    ./runtm "binadd.tm" "${TESTDIR}/$1.tape"
    export RESULT=$?
if [[ x$RESULT != x$2 ]]; then exit 1; fi
} |
grep -e 'accepted' |
diff -w - "${TESTDIR}/$1.tmout"
export RESULT=$?
rm -rf $SCRATCHDIR
exit $RESULT
