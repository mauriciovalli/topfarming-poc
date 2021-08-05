package topfarming.poc.infrastructure.repository

import io.lettuce.core.api.StatefulRedisConnection
import topfarming.poc.util.JsonUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Named


abstract class EntityRedisRepository<Entity> {
//    @Inject
//    protected lateinit var client: RedisClient

    @field:[Inject Named("entity")]
    protected lateinit var connection: StatefulRedisConnection<String, String>

    fun getValue(key: String): Entity {
        val commands = connection.sync()
        val value = commands.get(getKey(key))
        return JsonUtils.mapFromJson(value, getArgClass())
    }

    fun setValue(key: String, value: Entity): String {
        val commands = connection.sync()
        return commands.set(getKey(key), JsonUtils.mapToJson(value))
    }

    fun getKeys(): MutableList<String> {
        val commands = connection.sync()
        return commands.keys("*")
    }

    fun getClean() {
        val commands = connection.sync()
        getKeys().forEach { key -> commands.del(key) }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getArgClass(): Class<Entity> {
        val actualTypeArgument: Type =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.get(0)
        return actualTypeArgument as Class<Entity>
    }

    private val prefix: String = javaClass.simpleName
    private fun getKey(key: String): String {
        return prefix + "_" + key
    }
}
