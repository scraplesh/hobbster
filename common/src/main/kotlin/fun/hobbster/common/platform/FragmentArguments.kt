package `fun`.hobbster.common.platform

import android.os.Bundle
import android.os.put
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> argumentNotNull(): ReadWriteProperty<Fragment, T> = FragmentArgumentDelegate()

fun <T> argument(): ReadWriteProperty<Fragment, T?> = FragmentNullableArgumentDelegate()

private class FragmentArgumentDelegate<T> : ReadWriteProperty<Fragment, T> {
  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    val key = property.name
    return thisRef.arguments
      ?.get(key) as? T
      ?: throw IllegalStateException("Property ${property.name} could not be read")
  }

  override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
    val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
    val key = property.name
    args.put(key, value)
  }
}

private class FragmentNullableArgumentDelegate<T : Any?> : ReadWriteProperty<Fragment, T?> {
  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
    val key = property.name
    return thisRef.arguments?.get(key) as? T
  }

  override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
    val args = thisRef.arguments ?: Bundle().also(thisRef::setArguments)
    val key = property.name
    value?.let { args.put(key, it) } ?: args.remove(key)
  }
}
