#!/usr/bin/env bash


mvn -B clean site:site site -DtestFailureIgnore=true --fail-never -DskipITs=false -P site
mvn -B site:stage -DtestFailureIgnore=true --fail-never  -P site

echo "Clean old docs folder"
rm -rf docs/*

echo "Deploy new site"
mvn -B site:deploy -DtestFailureIgnore=true --fail-never  -P site

git add docs

git commit -a -m "Update docs"
