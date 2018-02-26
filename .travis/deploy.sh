#!/usr/bin/env bash
if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
	if [ "$TRAVIS_BRANCH" == 'master' ] || [ "$TRAVIS_BRANCH" == 'develop' ]; then

		openssl aes-256-cbc -K $encrypted_60abb3d8cccc_key -iv $encrypted_60abb3d8cccc_iv -in .travis/codesigning.asc.enc -out .travis/codesigning.asc -d
		gpg --fast-import .travis/codesigning.asc

		mvn deploy -P ossrh --settings .travis/mvnsettings.xml
	fi
fi