package common;

import java.util.List;

public interface MovesListener<T> {
    void notify(List<T> possibleMoves);
}
