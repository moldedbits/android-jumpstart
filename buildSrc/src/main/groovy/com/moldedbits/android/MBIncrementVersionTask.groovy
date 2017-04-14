package com.moldedbits.android

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

class MBIncrementVersionTask extends DefaultTask {
    def versionFileName = "version.properties"

    class VersionNo {
        int major=1
        int minor=0
        int patch=0
    }

    @TaskAction
    def incrementVersionCode() {
        // check if version file exists

        def versionFile = new File(versionFileName)
        if(versionFile.canRead()) {
            println("Yay! we have a version file")
            VersionNo version = new JsonSlurper().parse(versionFile)

            version.patch += 1

            // if its
            if(version.patch == 10) {
                version.minor += 1
                version.patch = 0
            }

            versionFile.write(JsonOutput.toJson(version))
        } else {
            initVersionFile()
        }
    }

    def File initVersionFile() {
        def versionFile = new File(versionFileName)
        // lets create a file
        println("Creating a new version file")
        // and put default values in it
        versionFile.write(JsonOutput.toJson(new VersionNo()))
        return versionFile
    }

    def String getVersionName() {
        VersionNo version = getVersionFromFile()
        // naming convention is
        // major(2char).minor(2char).patch(2char)
        // lets get strings in way that naming looks like 1.01.01
        String patch = version.patch
        if(version.patch < 10)
            patch = patch.padLeft(2, '0')

        String minor = version.minor
        if(version.minor < 10)
            minor = minor.padLeft(2, '0')

        return "" + version.major + "." + minor + "." + patch
    }

    def int getVersionCode() {
        VersionNo version = getVersionFromFile()
        return version.patch + version.minor * 10 + version.major * 100
    }

    private def VersionNo getVersionFromFile() {
        def versionFile = new File(versionFileName)
        if(!versionFile.canRead()) {
            versionFile = initVersionFile()
        }

        VersionNo version = new JsonSlurper().parse(versionFile)
        return version
    }
}
