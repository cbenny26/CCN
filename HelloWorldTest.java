import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * A simple test class to verify the functionality of HelloWorld and HelloWorldApp classes.
 * This class captures the standard output and verifies that "Hello World" is printed.
 */
public class HelloWorldTest {
    /**
     * The main method that runs tests for both HelloWorld classes.
     * 
     * @param args Command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        // Test HelloWorld class
        boolean test1Passed = testHelloWorldOutput("HelloWorld");
        System.out.println("HelloWorld test: " + (test1Passed ? "PASSED" : "FAILED"));
        
        // Test HelloWorldApp class
        boolean test2Passed = testHelloWorldOutput("HelloWorldApp");
        System.out.println("HelloWorldApp test: " + (test2Passed ? "PASSED" : "FAILED"));
        
        // Summary
        if (test1Passed && test2Passed) {
            System.out.println("All tests passed successfully!");
        } else {
            System.out.println("Some tests failed!");
            System.exit(1);
        }
    }
    
    /**
     * Tests if the specified class outputs "Hello World" when its main method is executed.
     * 
     * @param className The name of the class to test (either "HelloWorld" or "HelloWorldApp")
     * @return true if the test passes, false otherwise
     */
    private static boolean testHelloWorldOutput(String className) {
        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            // Call the main method of the specified class using reflection
            if ("HelloWorld".equals(className)) {
                HelloWorld.main(new String[0]);
            } else if ("HelloWorldApp".equals(className)) {
                HelloWorldApp.main(new String[0]);
            } else {
                throw new IllegalArgumentException("Unknown class name: " + className);
            }
            
            // Get the captured output and verify it
            String output = outputStream.toString().trim();
            return "Hello World".equals(output);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }
}