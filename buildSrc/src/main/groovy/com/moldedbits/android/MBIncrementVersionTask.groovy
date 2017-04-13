package com.moldedbits.android

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class MBIncrementVersionTask extends DefaultTask {
    @TaskAction
    def incrementVersionCode() {
        println "Hello from task"
    }
}
