#!/bin/bash

file1=$1
newFile=$2

sed 's/[N,]//g' $file1 | tee $newFile

