package `fun`.hobbster.common

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T> didSet(crossinline onSet: T.() -> Unit) = object : ReadWriteProperty<Any?, T> {
  private var value: T? = null

  override fun getValue(thisRef: Any?, property: KProperty<*>) =
    value ?: throw IllegalStateException("Property ${property.name} should be initialized before get.")

  override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    this.value = value
    value.onSet()
  }
}
