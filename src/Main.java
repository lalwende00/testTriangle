import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String input;
        while(!(input = input(
                "Enter triangle lengths with format (a,b,c) or (a, b, c) to evaluate if it's a triangle" +
                        " and the nature of this triangle.\nEnter x to leave."
        )).equals("x")){
            try{
                System.out.println(triangle(splitInput(input)));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayTriangle(){
        System.out.println();
    }

    /*
     *  Method verifying that a double[3] describes a possible triangle or not.
     *  if the double[3] describes a triangle, it will return the nature of this triangle:
     *      "scalène", "isocèle" or "équilatéral"
     *  To verify that the double[3] describes a triangle, we
     *      *   look for the maximum length of the triangle.
     *      *   verify that this max length is strictly smaller than the sum of the other two lengths.
     **/
    private static String triangle(double[] lengths) throws Exception{
        return new Triangle(lengths).getType().toString();
    }

    /*
     *  Method aiming to clean an input with a very specific format and to parse that input into 3 positive number
     *  with a maximal value of 9.
     *      (a,b,c) or (a, b, c)
     *      where a, b and c are >=0 and <= 9
     *      a,b and c must be numbers.
     * */
    private static double[] splitInput(String input) throws Exception{
        //  We begin by removing any white spaces that could be entered before and after the coordinates.
        //  Then, we use the "," String to split the input.
        String[] splitInput = input.trim().split(",");
        double[] cleanInput = new double[3];
        // Test1:
        // We should have 3 resulting String, or the format isn't correct.
        Exception e = new Exception("Invalid format, please enter lengths with format (a,b,c) or (a, b, c)");
        if(splitInput.length != 3) throw e;
        else{
            cleanInput[0] = checkA(splitInput[0]);
            cleanInput[1] = checkB(splitInput[1]);
            cleanInput[2] = checkC(splitInput[2]);
        }
        return cleanInput;
    }

    /*
     *  Test2:
     *      The first element should be "(a", where a is a number between 0 and 9 both included
     * */
    private static double checkA(String a) throws Exception{
        if(a.charAt(0) != '(' || a.length() != a.trim().length())
            throw new Exception("Invalid format, please enter lengths with format (a,b,c) or (a, b, c)");
        else return checkNumber(a.substring(1));

    }

    /*
     *  Test3:
     *      The second element should be "b" or " b", where a is a number between 0 and 9 both included
     **/
    private static double checkB(String b) throws Exception{
        Exception e = new Exception("Invalid format, please enter lengths with format (a,b,c) or (a, b, c)");
        // get a version of b without any " " in it.
        String trimmedB = b.trim();

        if(b.length() == trimmedB.length()) return checkNumber(b);
        else {
            if(b.length() - 1 == trimmedB.length()){
                if(b.charAt(0) != ' ') throw e;
                else return checkNumber(trimmedB);
            }
            else throw e;
        }
    }

    /*
     *  Test4:
     *      The third element should be "c)" or " c)"
     *      First, we need to check that the parenthesis is the last character.
     **/
    private static double checkC(String c) throws Exception{
        Exception e = new Exception("Invalid format, please enter lengths with format (a,b,c) or (a, b, c)");
        String cleanC;
        int lastIndex = c.length() - 1;
        if(c.charAt(lastIndex) != ')') throw e;
        else cleanC = c.substring(0, lastIndex);
        // At this point, we must have "c" or " c"
        // The test is therefore the exact same test as test3.
        return checkB(cleanC);
    }

    /*
     *  Testing that the number entered by the user is:
     *      *   a number
     *      *   between 0 and 9
     * */
    private static double checkNumber(String numberCandidate) throws Exception{
        try{
            // If the candidate is not a number, an exception will be thrown here
            double numberToTest = Double.parseDouble(numberCandidate);
            if(numberToTest >= 0 && numberToTest <= 9) return numberToTest;
            else throw new Exception("a has to be between 0 and 9");
        } catch (NumberFormatException e){ throw new Exception("a b and c have to be numbers"); }
    }


    /*
     *  I/O method to get an input from the user
     * */
    private static String input(String prompt){
        System.out.println(prompt);
        try{ return new BufferedReader(new InputStreamReader(System.in)).readLine(); }
        catch (Exception e){
            System.out.println("An error occured");
            return input(prompt);
        }
    }
}
