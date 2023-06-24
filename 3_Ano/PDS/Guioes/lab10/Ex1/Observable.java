package lab10.Ex1;

import java.util.HashSet;
import java.util.Set;

public class Observable {
    Set<Observer> listOfObservers = new HashSet<>();

    public void subscribe(Observer observer) {
        listOfObservers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        listOfObservers.remove(observer);
    }
}
