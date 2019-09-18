package common;

public interface Heuristical<T> {
    int getWeight(T state,T finalState);
}
