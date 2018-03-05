#!/usr/bin/env bash
if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
	CURRENT_VERSION=`xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' pom.xml`

	if [[ $CURRENT_VERSION == *-SNAPSHOT ]]; then
		NEW_VERSION=${CURRENT_VERSION%'-SNAPSHOT'}
		NEXT_VERSION=`bash ci/semver.sh -p $NEW_VERSION`
		NEXT_SNAPSHOT="$NEXT_VERSION-SNAPSHOT"
		echo "perform release of $NEW_VERSION from $CURRENT_VERSION and set next develop version $NEXT_SNAPSHOT"

		mvn versions:set -DnewVersion=$NEW_VERSION
		mvn versions:commit

        echo "commit new release version"
		git commit -a -m "Release $NEW_VERSION: set master to new release version"

		echo "Update version in README.md"
		sed -i -e "s|<version>[0-9A-Za-z._-]\{1,\}</version>|<version>$NEW_VERSION</version>|g" README.md && rm -f README.md-e
		git commit -a -m "Release $NEW_VERSION: Update README.md"

		echo "Build maven site"
		source ci/mvn-site.sh

		echo "create tag for new release"
		git tag -a $NEW_VERSION -m "Release $NEW_VERSION: tag release"

		echo "merge master back to develop"
		git checkout -f -b develop github/develop
		git merge master

		mvn versions:set -DnewVersion=$NEXT_SNAPSHOT
		mvn versions:commit

		echo "commit new snapshot version"
		git commit -a -m "Release $NEW_VERSION: set develop to next development version $NEXT_SNAPSHOT"

		git push github --all
		git push github --tags

	fi
fi