#!/usr/bin/env bash

git config --global user.email "oss@namics.com"
git config --global user.name "Namics OSS CI"

ORIGIN=`git config --get remote.origin.url`
GITHUB="https://${GITHUB_TOKEN}@${ORIGIN#'https://'}"
git remote add github $GITHUB
git fetch github

if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
	git checkout -qf $TRAVIS_BRANCH
fi;

