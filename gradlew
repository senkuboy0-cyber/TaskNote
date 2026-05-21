#!/bin/sh

#
# Copyright © 2015-2021 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#
# Gradle startup script for POSIX shell
#

# Determine which Java binary to use for the JVM
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
else
    JAVACMD=""
fi

if [ ! "$JAVACMD" ] ; then
    if [ -d "/usr/bin/java" ] ; then
        JAVACMD="/usr/bin/java"
    elif [ -d "/usr/java" ] ; then
        JAVACMD="/usr/java/bin/java"
    elif [ -d "/usr/local/bin/java" ] ; then
        JAVACMD="/usr/local/bin/java"
    elif [ -d "/usr/local/java" ] ; then
        JAVACMD="/usr/local/java/bin/java"
    fi
fi

if [ ! "$JAVACMD" ] ; then
    echo "ERROR: Cannot find a Java JDK or JRE installation to run Gradle.\n"
    echo "Please set JAVA_HOME to a JDK or JRE installation directory."
    echo "1>&2"
    exit 1
fi

# Set up classpath
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# Execute Gradle
"$JAVACMD" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
