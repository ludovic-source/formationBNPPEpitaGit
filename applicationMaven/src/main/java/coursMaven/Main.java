package coursMaven;

import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {

        String chaine = "TOTO";
        if (StringUtils.isAllLowerCase(chaine) == true) {
            System.out.println("minuscule");
        }
        if (StringUtils.isAllUpperCase(chaine) == true) {
            System.out.println("majuscule");
        }

    }

}
