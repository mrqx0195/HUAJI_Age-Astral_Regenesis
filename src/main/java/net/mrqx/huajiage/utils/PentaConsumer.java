package net.mrqx.huajiage.utils;

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@FunctionalInterface
public interface PentaConsumer<T, U, V, W, X> {
    void accept(T var1, U var2, V var3, W var4, X var5);

    default PentaConsumer<T, U, V, W, X> andThen(PentaConsumer<? super T, ? super U, ? super V, ? super W, ? super X> after) {
        return (t, u, v, w, x) -> {
            this.accept(t, u, v, w, x);
            after.accept(t, u, v, w, x);
        };
    }
}
