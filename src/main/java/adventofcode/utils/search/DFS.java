package adventofcode.utils.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DFS<T extends Traversable<A>, A extends Invertible<A>> {
    private final T traversable;
    private final List<A> actions = new ArrayList<>();
    private final List<Iterator<A>> iterators = new ArrayList<>();
    private Iterator<A> iterator;

    public DFS(T traversable) {
        this.traversable = traversable;
        iterator = traversable.actions().iterator();
    }

    public void clear() {
        actions.clear();
        iterators.clear();
        iterator = traversable.actions().iterator();
    }

    public int depth() {
        return actions.size();
    }

    public boolean advance() {
        while (true) {
            if (iterator.hasNext()) {
                A action = iterator.next();
                if (actions.isEmpty() || !action.equals(actions.get(actions.size() - 1).inverse())) {
                    if (traversable.tryApply(action)) {
                        actions.add(action);
                        iterators.add(iterator);
                        iterator = traversable.actions().iterator();
                        return true;
                    }
                }
            } else {
                if (actions.isEmpty()) {
                    return false;
                }
                traversable.apply(actions.get(actions.size() - 1).inverse());
                actions.remove(actions.size() - 1);
                iterator = iterators.remove(iterators.size() - 1);
            }
        }
    }
}
