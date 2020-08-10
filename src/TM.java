import java.io.*;

public class TM {


    public static void main(String[] args) throws IOException {

        // TM description file
        // Even if it not tested in the specs, I have a minimum of error handling concerning arguments
        if (args.length!=2 && args.length!=1){
            System.out.println("enter valid number of arguments.");
            System.exit(3);
        }
        // Description file object
        File desc = new File(args[0]);

        if (args.length==2) {
            // Setting value of the desc file and creating and buffered reader
            File tape = new File(args[1]);
            BufferedReader br = null;
            // Try catch block in case of a File not found exception
            try{
            br = new BufferedReader(new FileReader(tape));
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found !");
                System.exit(3);
            }
            String st;
            String tapeFile = "";
            // Concatenating the Tape's file lines to have 1 long continuous tape
            while ((st = br.readLine()) != null)
                tapeFile = tapeFile.concat(st);
            // Empty tape means adding a blank character " _ "
            Machine TM1 = Description.process(desc);
            if (tapeFile.equals("")){
                tapeFile = "_";
            }
            TM1.Run(tapeFile);
            br.close();
        } else {
            String st = "_";
            Machine TM1 = Description.process(desc);
            TM1.Run(st);
        }
    }
}
