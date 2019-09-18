package common;

import java.util.List;

public interface Movable<T> {
    List<T> getPossibleMoves();
}
