#!/usr/bin/env bash

classpath=:../lib/algs4.jar:../lib/twitter4j-core-4.0.7.jar

cd run || exit

java -cp "${classpath}" MainDriver