package test;

public class AutreTest {

    public void transformationVariable(String variable) {
        variable = "Luffy";
    }

    public void autreTransfo1() {
        String str = "no";
        autreTransfo2(str);
        System.out.println("transfo réussie ? " + str);
    }

    public void autreTransfo2 (String str) {
        str = "yes";
    }
}
