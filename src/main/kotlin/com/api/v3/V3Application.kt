package com.api.v3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.modulith.core.ApplicationModules

@SpringBootApplication
class V3Application

fun main(args: Array<String>) {
	val modules = ApplicationModules.of(V3Application::class.java)
	//modules.forEach { println(it) }
	runApplication<V3Application>(*args)
}
