package test;

public class MainTest {

    public static void main(String[] args) {

        Test test = new Test();
        String variableMain = test.getVariable();
        AutreTest autreTest = new AutreTest();

        //autreTest.transformationVariable(test.getVariable());
        //test.transformationVariable(test.getVariable());
        autreTest.transformationVariable(variableMain);
        test.transformationVariable(variableMain);

        //System.out.println(test.getVariable());
        System.out.println(variableMain);

        autreTest.autreTransfo1();

        final String message = "One piece";
        String messageBis = message;
        messageBis = "bingo";
        System.out.println(messageBis);

        // test pour démontrer qu'une classe déclarée en "final" n'est pas immuable
        final TestClassFinal classeFinal = new TestClassFinal();
        System.out.println("class final - message avant =  " + classeFinal.getMessage());
        classeFinal.setMessage("class immuable démontré");
        System.out.println("class final - message après =  " + classeFinal.getMessage());

    }
}
