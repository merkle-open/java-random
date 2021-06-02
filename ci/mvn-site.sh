#!/usr/bin/env bash


mvn clean site:site site -DtestFailureIgnore=true --fail-never -DskipITs=false -P site --settings ci/mvnsettings.xml
mvn site:stage -DtestFailureIgnore=true --fail-never  -P site

echo "Clean old docs folder"
rm -rf docs/*

echo "Deploy new site"
mvn site:deploy -DtestFailureIgnore=true --fail-never  -P site

git add docs

git commit -a -m "Update docs"
