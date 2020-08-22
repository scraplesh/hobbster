package `fun`.hobbster.domain

/**
 * Compat version of [java.util.function.Consumer]
 * @param <T> the type of the input to the operation
</T> */
interface Consumer<T> {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    fun accept(t: T)
}
