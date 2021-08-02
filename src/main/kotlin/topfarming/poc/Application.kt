package topfarming.poc

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("topfarming.poc")
                .mainClass(Application.javaClass)
                .start()
    }

}