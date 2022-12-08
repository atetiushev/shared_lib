package com.example.json.converter

import com.example.model.Config
import groovy.json.JsonSlurper

class ConfigReader implements Serializable {
    def steps

    ConfigReader(steps) {
        this.steps = steps
    }

    def parseConfig(content) {
        this.steps.println content
        def jsonSlurper = new JsonSlurper()
        Config parsed = jsonSlurper.parseText(content) as Config
        this.steps.println parsed

        this.steps.println "try to get object " + parsed.instances.size();
        this.steps.println "category " + parsed.instances.get(0).category
        this.steps.println "category " + parsed.instances.get(0).instance_name
    }

}