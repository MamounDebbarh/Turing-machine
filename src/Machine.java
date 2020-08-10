
import java.util.*;


public class Machine {
    private HashMap<String,String> stateSet;
    // modif here
    // private Set<Transition> transSet;
    private HashMap<String , Transition> transSet;
    private HashMap<Character,Character> alphabetSet;
    private String startS;
    private String acceptS;
    private String rejectS;
    private String currentS;
    private String tape;
    private int symbol;

    class Transition {
        String readState;
        char readSymbol;
        String writeState;
        char writeSymbol;
        boolean moveDirection;	//true is right, false is left

        boolean isConflicting(String state, char symbol)
        {
            return state.equals(readState) && symbol == readSymbol;
        }
    }


    public Machine()
    {
        stateSet = new HashMap<>();
        transSet = new HashMap<>();
        alphabetSet = new HashMap<>();
        alphabetSet.put('_','_');
        startS = "";
        acceptS = "";
        rejectS = "";
        tape = "";
        currentS = "";
        symbol = 0;

    }

    void Run(String input) {
        currentS = startS;
        tape = input.replaceAll("\\s+", "");
        int i = -1;
        boolean stuck = false;
        while(!currentS.equals(acceptS) && !currentS.equals(rejectS)) {
            if (!stuck) {
                i++;
            }
            boolean foundTransition = false;
            Transition CurrentTransition = null;


            // modif here
//            Iterator<Transition> TransitionsIterator = transSet.iterator();
//            while ( TransitionsIterator.hasNext() && !foundTransition) {
//                Transition nextTransition = TransitionsIterator.next();
//                if (nextTransition.readState.equals(currentS) && nextTransition.readSymbol == tape.charAt(symbol))
//                {
//                    foundTransition = true;
//                    CurrentTransition = nextTransition;
//                }
//            }


            if (transSet.get(currentS+tape.charAt(symbol)) != null){
                foundTransition = true;
                CurrentTransition = transSet.get(currentS+tape.charAt(symbol));
            }

//            && !alphabetSet.contains(tape.charAt(symbol))
//            System.out.println(" current state : " + currentS);
//            System.out.println(" index : " + symbol);
//            System.out.println(" " +tape);


//            System.out.println(" " + i);
            if (!foundTransition && alphabetSet.get(tape.charAt(symbol)) == null) {
                System.out.println("input error");
                System.exit(2);
            }
            else if (!foundTransition && alphabetSet.get(tape.charAt(symbol)) != null){
                stuck = true;
            }
            else if (CurrentTransition != null && !stuck) {
                currentS = CurrentTransition.writeState;
                char[] tempTape = tape.toCharArray();
                tempTape[symbol] = CurrentTransition.writeSymbol;

//                System.out.println(" tape state :"+lastTrans);
                tape =  new String(tempTape);
                if(CurrentTransition.moveDirection) {
                    symbol++;
                }
                else {
                    symbol--;
                }
                // maybe left move when at start of tape. if so it's an error.
                if (symbol < 0 && !currentS.equals(acceptS)){
                    symbol = 0;
                }

                while (tape.length() <= symbol) {
                    tape = tape.concat("_");
                }

            }
            if (stuck){
                symbol++;
                if(symbol == tape.length()){
                    break;
                }
            }
        }

        // removing blanks at the end of Tape string
        tape = tape.replaceAll("_$","");
        if (tape.equals("")){
            tape = "_";
        }


        if (currentS.equals(acceptS)){
            System.out.println("accepted");
            System.out.println(i);
            System.out.println(tape);
        }
        else{
            System.out.println("not accepted");
            System.out.println(i);
            System.out.println(tape);
        }

    }

    void addState(String newState)
    {
        stateSet.putIfAbsent(newState, newState);
    }

    void setStartState(String newStartState)
    {
        if (stateSet.get(newStartState) != null) {
            startS = newStartState;
        }
    }

    void setAcceptState(String newAcceptState)
    {
        if (stateSet.get(newAcceptState) != null && !rejectS.equals(newAcceptState)) {
            acceptS = newAcceptState;
        }
    }

    void setRejectState(String newRejectState)
    {
        if (stateSet.get(newRejectState) != null && !acceptS.equals(newRejectState)) {
            rejectS = newRejectState;
        }
    }

    void addTransition(String rState, char rSymbol, String wState, char wSymbol, boolean mDirection)
    {
        if(stateSet.get(rState) == null || stateSet.get(wState) == null || alphabetSet.get(rSymbol) == null || alphabetSet.get(wSymbol) == null) {
            System.out.println("input error");
            System.exit(2);
        }


        // modif here
//        boolean conflict = false;
//        Iterator<Transition> TransitionsIterator = transSet.iterator();
//        while ( TransitionsIterator.hasNext() && !conflict) {
//            Transition nextTransition = TransitionsIterator.next();
//            if (nextTransition.isConflicting(rState, rSymbol)) {
//                conflict = true;
//            }
//
//        }

        boolean conflict = false;
        if (transSet.get(rState+rSymbol) != null){
            conflict = true;
        }


        // modif here
//        if (!conflict) {
//            Transition newTransition = new Transition();
//            newTransition.readState = rState;
//            newTransition.readSymbol = rSymbol;
//            newTransition.writeState = wState;
//            newTransition.writeSymbol = wSymbol;
//            newTransition.moveDirection = mDirection;
//            transSet.add(newTransition);
//        } else {
//            System.out.println("input error");
//            System.exit(2);
//        }


        if (!conflict) {
            Transition newTransition = new Transition();
            newTransition.readState = rState;
            newTransition.readSymbol = rSymbol;
            newTransition.writeState = wState;
            newTransition.writeSymbol = wSymbol;
            newTransition.moveDirection = mDirection;
            transSet.put(rState+rSymbol , newTransition);
        } else {
            System.out.println("input error");
            System.exit(2);
        }
    }
    void addAlphabet(char alpha){
        alphabetSet.putIfAbsent(alpha, alpha);
    }
}