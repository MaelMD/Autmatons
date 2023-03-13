import java.util.*;

public class AFD {
    private Set<Integer> states;
    private Set<Character> alphabet;
    private Map<Integer, Map<Character, Integer>> transitions;
    private int startState;
    private Set<Integer> acceptStates;

    public AFD(Set<Integer> states, Set<Character> alphabet, 
               Map<Integer, Map<Character, Integer>> transitions, 
               int startState, Set<Integer> acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.startState = startState;
        this.acceptStates = acceptStates;
    }

    public boolean run(String inputString) {
        int currentState = startState;
        for (char symbol : inputString.toCharArray()) {
            if (!alphabet.contains(symbol)) {
                return false; // Reject invalid input
            }
            currentState = transitions.get(currentState).get(symbol);
            if (currentState == null) {
                return false; // Reject if transition not defined
            }
        }
        return acceptStates.contains(currentState);
    }
}
