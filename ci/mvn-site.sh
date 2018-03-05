#!/usr/bin/env bash

if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then

	mvn clean site:site site -DtestFailureIgnore=true --fail-never -DskipITs=false -P site --settings ci/mvnsettings.xml
	mvn site:stage -DtestFailureIgnore=true --fail-never  -P site

	echo "Clean old docs folder"
	rm -rf docs/*

	echo "Deploy new site"
	mvn site:deploy -DtestFailureIgnore=true --fail-never  -P site

	git add docs

	CURRENT_VERSION=`bash ci/current-version.sh`
	git commit -a -m "Version $CURRENT_VERSION: Update docs"
fi