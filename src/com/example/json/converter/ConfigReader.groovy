package com.example.json.converter
import groovy.json.JsonSlurper

class ConfigReader implements Serializable {
    def steps

    ConfigReader(steps) {
        this.steps = steps
    }

    def parseConfig(content) {
        def jsonSlurper = new JsonSlurper()
        def parsed = jsonSlurper.parse(content)
        this.steps.println parsed
    }

}