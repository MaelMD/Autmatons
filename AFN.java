import java.util.*;

public class AFN {
    private Set<Integer> states;
    private Set<Character> alphabet;
    private Map<Integer, Map<Character, Set<Integer>>> transitions;
    private int startState;
    private Set<Integer> acceptStates;

    public AFN(Set<Integer> states, Set<Character> alphabet, 
               Map<Integer, Map<Character, Set<Integer>>> transitions, 
               int startState, Set<Integer> acceptStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.startState = startState;
        this.acceptStates = acceptStates;
    }

    public boolean run(String inputString) {
        Set<Integer> currentStates = new HashSet<>();
        currentStates.add(startState);
        for (char symbol : inputString.toCharArray()) {
            if (!alphabet.contains(symbol)) {
                return false; // Reject invalid input
            }
            Set<Integer> nextStates = new HashSet<>();
            for (int state : currentStates) {
                if (transitions.get(state).containsKey(symbol)) {
                    nextStates.addAll(transitions.get(state).get(symbol));
                }
            }
            currentStates = nextStates;
        }
        for (int state : currentStates) {
            if (acceptStates.contains(state)) {
                return true;
            }
        }
        return false;
    }
}
