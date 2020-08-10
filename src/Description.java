import java.io.*;

public class Description {

    private Description() {}

    // Method that allows to quickly check is a String in an integer when parsed.
    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (str.length() == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    static Machine process(File file) throws IOException {
        Machine newTM = new Machine();
        BufferedReader br = null;
        String st;

        //Catching the file not found exception
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found !");
            System.exit(3);
        }

        // reading and setting accept state and reject state
        if ((st = br.readLine()) != null){
            String[] fline = st.split("\\s+");

            if (fline.length == 2) {
                // format "state n" check
                if (!fline[0].equals("states") || !isInteger(fline[1])) {
                    System.out.println("input error");
                    System.exit(2);
                }
                // Setting number of states
                int num = Integer.parseInt(fline[1]);
                System.out.println(" There are " + num + " states !");
                // For the number of states, we add each state to the state list and sent starting state, accepting state and rejecting state.
                for (int o = 0; o < num; o++) {
                    st = br.readLine();
                    if (st == null){
                        System.out.println("input error");
                        System.exit(2);
                    }
                    String[] line = st.split("\\s+");
                    // format "state (+/-)" check or not enough lines
                    if (line.length != 1 && line.length != 2 || line[0].equals("alphabet")){
                        System.out.println("input error");
                        System.exit(2);
                    }
                    newTM.addState(line[0]);
                    System.out.println(" State added : " + line[0]);
                    if (o == 0) {
                        newTM.setStartState(line[0]);
                        System.out.println(" Start state is :" + line[0]);
                    }
                    if (line.length > 1) {
                        if (line[1].equals("+")) {
                            newTM.setAcceptState(line[0]);
                            System.out.println(" Accept state is :" + line[0]);
                        } else {
                            newTM.setRejectState(line[0]);
                            System.out.println(" Reject state is :" + line[0]);
                        }
                    }
                }
            } else {
                System.out.println("input error");
                System.exit(2);
            }
        } else {
            System.out.println("input error");
            System.exit(2);
        }
        // Alphabet check and adding characters to the alphabet
        if ((st = br.readLine()) != null){
            String[] fline = st.split("\\s+");
            if(fline[0].equals("alphabet") && isInteger(fline[1]) ){
                if (fline.length != 2 + Integer.parseInt(fline[1]) || Integer.parseInt(fline[1]) == 0 ) {
                    System.out.println("input error");
                    System.exit(2);
                }
                for (int i = 0 ; i < Integer.parseInt(fline[1]) ; i++){
                    // storing alphabet for checks
                    System.out.println(" storing alphabet process");
                    newTM.addAlphabet(fline[i+2].charAt(0));
                }
            } else {
                System.out.println("input error");
                System.exit(2);
            }
        } else {
            System.out.println("input error");
            System.exit(2);
        }

        int check = 0;
        // Read all the transition lines and adding transitions to the transition list
        while ((st = br.readLine()) != null && !st.equals("")){
            check ++;
            String[] fline = st.split("\\s+");
            if (fline.length == 5){
                boolean rl = true;
                if (fline[4].equals("L")){
                    rl = false;
                } else if (!fline[4].equals("R")){
                    System.out.println("input error");
                    System.exit(2);

                }
                newTM.addTransition(fline[0],fline[1].charAt(0),fline[2],fline[3].charAt(0),rl);
                System.out.println(" Transition added : " + st);
            } else {
                System.out.println("input error");
                System.exit(2);
            }
        }
        if (check == 0){
//            System.out.println(" " + check);
            System.out.println("not accepted\n" +
                    "0 \n" +
                    "_");
            System.exit(2);
        }
        br.close();
        return newTM;
    }
}
