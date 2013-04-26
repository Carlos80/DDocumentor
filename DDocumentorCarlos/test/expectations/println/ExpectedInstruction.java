package expectations.println;

import java.util.ArrayList;
import org.ddocumentor.files.javaclass.Instruction;
import org.ddocumentor.files.javaclass.JavaMethod;
import org.ddocumentor.files.javaclass.MethodCall;

/**
 *
 * @author carlos
 */
public class ExpectedInstruction {

    public static Instruction expectedInstruction = new Instruction();

    public ExpectedInstruction() {
    }

    public static void setPrintlnExpectedInstruction() {
        
        String expectedInstructionText = "System.out.println(22.35);";

        expectedInstruction.setText(expectedInstructionText);

        MethodCall methodCall = new MethodCall(expectedInstructionText);
        expectedInstruction.setMethodCall(methodCall);

        ArrayList<JavaMethod> printlnIsExampleOf = new ArrayList<JavaMethod>();
        printlnIsExampleOf.add(methodCall.getMethod());
        expectedInstruction.setIsExampleOf(printlnIsExampleOf);

    }



}
