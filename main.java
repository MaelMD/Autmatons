import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Define AFD
        Set<Integer> afdStates = new HashSet<>(Arrays.asList(0, 1, 2));
        Set<Character> afdAlphabet = new HashSet<>(Arrays.asList('a', 'b'));
        Map<Integer, Map<Character, Integer>> afdTransitions = new HashMap<>();
        afdTransitions.put(0, new HashMap<>(Map.of('a', 1, 'b', 2)));
        afdTransitions.put(1, new HashMap<>(Map.of('a', 1, 'b', 2)));
        afdTransitions.put(2, new HashMap<>(Map.of('a', 2, 'b', 2)));
        int afdStartState = 0;
        Set<Integer> afdAcceptStates = new HashSet<>(Collections.singletonList(1));
        AFD afd = new AFD(afdStates, afdAlphabet, afdTransitions, afdStartState, afdAcceptStates);

        // Define AFN
        Set<Integer> afnStates = new HashSet<>(Arrays.asList(0, 1, 2));
        Set<Character> afnAlphabet = new HashSet<>(Arrays.asList('a', 'b'));
        Map<Integer, Map<Character, Set<Integer>>> afnTransitions = new HashMap<>();
        afnTransitions.put(0, new HashMap<>(Map.of('a', new HashSet<>(Arrays.asList(0, 1)), 'b', new HashSet<>(Collections.singletonList(0)))));
        afnTransitions.put(1, new HashMap<>(Map.of('b', new HashSet<>(Collections.singletonList(2)))));
        afnTransitions.put(2, new HashMap<>(Map.of('a', new HashSet<>(Collections.singletonList(2)), 'b', new HashSet<>(Collections.singletonList(2))))); 
        int afnStartState = 0;
        Set<Integer> afnAcceptStates = new HashSet<>(Collections.singletonList(2));
        AFN afn = new AFN(afnStates, afnAlphabet, afnTransitions, afnStartState, afnAcceptStates);

        // Test AFD
        System.out.println("Testing AFD:");
        System.out.println("Accepted: " + afd.run("ab"));
        System.out.println("Accepted: " + afd.run("aab"));
        System.out.println("Rejected: " + afd.run("bb"));

        // Test AFN
        System.out.println("Testing AFN:");
        System.out.println("Accepted: " + afn.run("ab"));
        System.out.println("Accepted: " + afn.run("aab"));
        System.out.println("Rejected: " + afn.run("bb"));
    }
}
