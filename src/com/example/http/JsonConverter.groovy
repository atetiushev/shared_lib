package com.example.http
import groovy.json.JsonSlurper

class JsonConverter implements Converter {
    def convert(toConvert) {
        def slurper = new JsonSlurper()
        return slurper.parseText(toConvert)
    }
}