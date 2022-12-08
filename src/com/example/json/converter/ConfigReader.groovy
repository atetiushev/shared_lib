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
        Config config = jsonSlurper.parseText(content) as Config
        this.steps.println config
        dealWithConfig(config)
    }

    def dealWithConfig(Config config) {
        this.steps.println "try to get object " + config.instances.size();
        this.steps.println "category " + config.instances.get(0).category
        this.steps.println "instance_name " + config.instances.get(0).instance_name
        this.steps.println "parameters " + config.instances.get(0).standalone_jobs.size();
        this.steps.println "repo " + config.instances.get(0).standalone_jobs.get(0).repo;
        this.steps.println "type " + config.instances.get(0).standalone_jobs.get(0).type;
    }

}