import java.util.*;

public class AFN {
    private Set<String> states;
    private Set<String> acceptingStates;
    private String startState;
    private Map<String, Map<Character, Set<String>>> transitions;

    public AFN(Set<String> states, Set<String> acceptingStates, String startState, Map<String, Map<Character, Set<String>>> transitions) {
        this.states = states;
        this.acceptingStates = acceptingStates;
        this.startState = startState;
        this.transitions = transitions;
    }

    public boolean accepts(String input) {
        Set<String> currentStates = new HashSet<>();
        currentStates.add(startState);

        for (char c : input.toCharArray()) {
            Set<String> nextStates = new HashSet<>();
            for (String state : currentStates) {
                Set<String> possibleNextStates = transitions.get(state).get(c);
                if (possibleNextStates != null) {
                    nextStates.addAll(possibleNextStates);
                }
            }
            currentStates = nextStates;
        }

        return !Collections.disjoint(currentStates, acceptingStates);
    }

    public static void main(String[] args) {
        Set<String> states = new HashSet<>();
        states.add("q0");
        states.add("q1");
        states.add("q2");

        Set<String> acceptingStates = new HashSet<>();
        acceptingStates.add("q2");

        String startState = "q0";

        Map<String, Map<Character, Set<String>>> transitions = new HashMap<>();
        Map<Character, Set<String>> q0Transitions = new HashMap<>();
        Set<String> q0TransitionsA = new HashSet<>();
        q0TransitionsA.add("q1");
        q0Transitions.put('a', q0TransitionsA);
        Set<String> q0TransitionsB = new HashSet<>();
        q0TransitionsB.add("q0");
        transitions.put("q0", q0Transitions);

        Map<Character, Set<String>> q1Transitions = new HashMap<>();
        Set<String> q1TransitionsA = new HashSet<>();
        q1TransitionsA.add("q2");
        q1Transitions.put('a', q1TransitionsA);
        Set<String> q1TransitionsB = new HashSet<>();
        q1TransitionsB.add("q0");
        transitions.put("q1", q1Transitions);

        Map<Character, Set<String>> q2Transitions = new HashMap<>();
        Set<String> q2TransitionsA = new HashSet<>();
        q2TransitionsA.add("q2");
        q2Transitions.put('a', q2TransitionsA);
        Set<String> q2TransitionsB = new HashSet<>();
        q2TransitionsB.add("q2");
        transitions.put("q2", q2Transitions);

        AFN afn = new AFN(states, acceptingStates, startState, transitions);
        System.out.println(afn.accepts("aba")); // output: true
        System.out.println(afn.accepts("abab")); // output: false
    }
}
