#!/usr/bin/env bash
if ! [ -x "$(command -v xmllint)" ]; then
	sudo -E apt-get -yq --no-install-suggests --no-install-recommends --force-yes install libxml2-utils &>/dev/null
fi
xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' pom.xml