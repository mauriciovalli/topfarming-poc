package topfarming.poc.infrastructure.repository

import io.lettuce.core.api.StatefulRedisConnection
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DefaultRedisRepository {
    @field:[Inject Named("default")]
//    @Inject
    private lateinit var connection: StatefulRedisConnection<String, Any>

    fun getValue(key: String): Any {
        val commands = connection.sync()
        return commands.get(key)
    }

    fun setValue(key : String,  value : Any) : String{
        val commands = connection.sync()
        return commands.set(key, value)
    }

    fun getKeys(): List<String> {
        val commands = connection.sync()
        return commands.keys("*")
    }

    fun invalidateCache() {
        val commands = connection.sync()
        getKeys().forEach { key -> commands.del(key) }
    }
}