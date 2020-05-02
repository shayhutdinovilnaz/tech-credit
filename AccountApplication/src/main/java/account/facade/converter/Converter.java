package account.facade.converter;

public interface Converter<T, S> {
    T convert(S source);
}
