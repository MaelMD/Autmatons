import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AFD {
    private Set<String> states;
    private Set<String> acceptingStates;
    private String startState;
    private Map<String, Map<Character, String>> transitions;

    public AFD(Set<String> states, Set<String> acceptingStates, String startState, Map<String, Map<Character, String>> transitions) {
        this.states = states;
        this.acceptingStates = acceptingStates;
        this.startState = startState;
        this.transitions = transitions;
    }

    public boolean accepts(String input) {
        String currentState = startState;
        for (char c : input.toCharArray()) {
            currentState = transitions.get(currentState).get(c);
            if (currentState == null) {
                return false;
            }
        }
        return acceptingStates.contains(currentState);
    }

    public static void main(String[] args) {
        Set<String> states = new HashSet<>();
        states.add("q0");
        states.add("q1");
        states.add("q2");

        Set<String> acceptingStates = new HashSet<>();
        acceptingStates.add("q2");

        String startState = "q0";

        Map<String, Map<Character, String>> transitions = new HashMap<>();
        Map<Character, String> q0Transitions = new HashMap<>();
        q0Transitions.put('a', "q1");
        q0Transitions.put('b', "q0");
        transitions.put("q0", q0Transitions);

        Map<Character, String> q1Transitions = new HashMap<>();
        q1Transitions.put('a', "q2");
        q1Transitions.put('b', "q0");
        transitions.put("q1", q1Transitions);

        Map<Character, String> q2Transitions = new HashMap<>();
        q2Transitions.put('a', "q2");
        q2Transitions.put('b', "q2");
        transitions.put("q2", q2Transitions);

        AFD afd = new AFD(states, acceptingStates, startState, transitions);
        System.out.println(afd.accepts("aba")); // output: true
        System.out.println(afd.accepts("abab")); // output: false
    }
}
