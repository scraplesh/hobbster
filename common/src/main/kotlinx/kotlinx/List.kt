package kotlinx

infix fun <T> List<T>.equalTo(other: List<T>): Boolean =
    size == other.size &&
            mapIndexed { index: Int, item: T -> item == other[index] }
                .all { it }
