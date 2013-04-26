package testers;

import org.ddocumentor.files.javaclass.Instruction;
import expectations.println.ExpectedInstruction;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author carlos
 */
public class InstructionTester {

    public static void canSavePrintlnInstruction(String actualInstructionText) {

        ExpectedInstruction.setPrintlnExpectedInstruction();

        assertThat(actualInstructionText,
                is(equalTo(ExpectedInstruction.expectedInstruction.getText())));

        Instruction actualInstruction = new Instruction(actualInstructionText);

        assertThat(actualInstruction.getMethodCall().getText(),
                is(equalTo(ExpectedInstruction.expectedInstruction
                .getMethodCall().getText())));

        assertThat(actualInstruction.getIsExampleOf().get(0).getName(),
                is(equalTo(ExpectedInstruction.expectedInstruction
                .getIsExampleOf().get(0).getName())));

    }
}
