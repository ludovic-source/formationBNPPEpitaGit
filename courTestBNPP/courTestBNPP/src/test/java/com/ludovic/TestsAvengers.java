package com.ludovic;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestsAvengers {

    private Humanoid peterParker = new Humanoid("Peter Parker", 15);
    private Humanoid tonyStark = new Humanoid("Tony Stark", 48);
    private Humanoid bruceBanner = new Humanoid("Bruce Banner", 48);
    private Humanoid thorOfAsgard = new Humanoid("Thor of Asgard", 1500);
    private Humanoid natashaRomanoff = new Humanoid("Natasha Romanoff", 33);
    private Humanoid steveRogers = new Humanoid("Steve Rogers", 106);
    private Humanoid clintonBarton = new Humanoid("Clinton Barton", 35);
    private Humanoid thanosTheTitan = new Humanoid("Thanos the Titan", 750000);

    private SuperHero spiderman = new SuperHero(peterParker, "Spider-man", Arrays.asList("Web Shooting", "Agility"));
    private SuperHero ironman = new SuperHero(tonyStark, "Iron Man", Arrays.asList("High-tech armor", "Rich"));
    private SuperHero hulk = new SuperHero(bruceBanner, "Hulk", Arrays.asList("Strong"));
    private SuperHero thor = new SuperHero(thorOfAsgard, null, Arrays.asList("Mjöllnir", "Immortal"));
    private SuperHero blackWidow = new SuperHero(natashaRomanoff, "Black Widow", Arrays.asList("Close Combat"));
    private SuperHero captainAmerica = new SuperHero(steveRogers, "Captain America", Arrays.asList("Vibranium Shield", "Super-soldier"));
    private SuperHero hawkEye = new SuperHero(clintonBarton, "Hawk Eye", Arrays.asList("Bow and arrows"));
    private SuperHero thanos = new SuperHero(thanosTheTitan, null, Arrays.asList("Strong", "Infinity Gauntlet"));

    private List<SuperHero> listeAvengers = Arrays.asList(spiderman, ironman, hulk, thor,
            blackWidow, captainAmerica, hawkEye);

    private double tempsDebut;
    private double duree;

    @BeforeEach
    public void beforeEach() {
        tempsDebut = System.currentTimeMillis();
    }

    @AfterEach
    public void afterEach() {
        duree = System.currentTimeMillis() - tempsDebut;
        System.out.println("temps d'exécution du test = " + duree);
    }

    @Order(1)
    @Test
    @DisplayName("Spiderman devrait être mineur")
    public void should_Verify_Spiderman_Mineur() {
        int ageSpiderman = spiderman.getAge();
        assertThat(ageSpiderman).isLessThan(17);
    }

    @Test
    @Order(2)
    public void shouldf_Verify_BlackWidow_Is_AnAvengers() {
        assertThat(listeAvengers).contains(blackWidow);
    }

    @Test
    @Order(3)
    public void should_Verifier_Une_Fois_Le_Meme_Avenger() {
        assertThat(listeAvengers).containsOnlyOnceElementsOf(listeAvengers);
    }

    @Test
    @Order(3)
    public void should_Verifier_Que_Thanos_Est_Pas_Un_Avengers() {
        assertThat(listeAvengers).doesNotContain(thanos);
    }

    @Test
    @Order(4)
    public void should_Verifier_Que_Thor_Et_Thanos_Pas_de_nom_superhero() {
        assertThat(thor.getHeroName()).isNull();
        assertThat(thanos.getHeroName()).isNull();
    }

    @Test
    @Order(5)
    public void should_verifier_que_thanos_possede_au_moins_memes_pouvoirs_que_hulk() {
        assertThat(thanos.getPowers()).containsOnlyOnceElementsOf(hulk.getPowers());
    }

    @Test
    @Order(6)
    public void should_verifier_types_superhero_humanoid_string() {
        //assertThat(hawkEye).is(hawkEye instanceof Humanoid.class);
        //assertThat(clintonBarton).asString();
        assertThat(hawkEye).isInstanceOf(SuperHero.class).isInstanceOf(Humanoid.class);
        assertThat(clintonBarton).isNotInstanceOf(SuperHero.class).isNotInstanceOf(String.class);
    }

    @Test
    @Order(7)
    public void tester_egalite_age_tony_stark_et_bruce_banner() {
        //assertThat(tonyStark.getAge()).isEqualTo(bruceBanner.getAge());
        assertThat(tonyStark).isEqualToComparingOnlyGivenFields(bruceBanner, "age");
    }

    @Test
    @Order(8)
    public void tester_regexp() {
        // NE FONCTIONNE PAS
        //assertThat(ironman.getHeroName()).contains(" ");
        //assertThat(spiderman.getHeroName()).doesNotContain(" ");
        assertThat(ironman.getHeroName()).matches(".* .*");
        assertThat(spiderman.getHeroName()).matches(".*-.*");
    }

    @Test
    public void verifier_ne_pas_pouvoir_ajouter_ou_supprimer_superpouvoir() {

    }

    @Test
    public void verifier_espace_dans_nom_superhero() {
        //SoftAssertions softly = new SoftAssertions();
        //for (SuperHero superHero : listeAvengers) {
        //    softly.assertThat(superHero.getHeroName()).withFailMessage(superHero.getHeroName()).matches(".* .*");
        //}
        //softly.assertAll();
        assertAll(
                () -> assertThat(spiderman.getHeroName()).withFailMessage("Il n'y pas d'espace dans spider-man")
                        .matches(".* .*"),
                () -> assertThat(ironman.getHeroName()).withFailMessage("Il n'y pas d'espace dans  iron man")
                        .matches(".* .*"));

    }

    @Disabled
    @Order(10)
    @DisplayName("Exemple du cours - Multiplying by 0 should return 0")
    @ParameterizedTest(name = "{index} - with i = ''{0}''")
    @ValueSource(doubles = {0.0, Double.MAX_VALUE, Double.POSITIVE_INFINITY})
    public void some_test(double i) {
        assertThat(i*0).isEqualTo(0.0);
    }

    @Order(9)
    @DisplayName("exemple de test du cours pour tester les exceptions")
    @Test
    // attention, pour utiliser les lambda, il faut passer en version 8, donc faire la modif dans pom.xml
    // <plugin>
    //          <artifactId>maven-compiler-plugin</artifactId>
    //          <version>3.8.0</version>
    //          <configuration>
    //            <source>8</source>
    //            <target>8</target>
    //          </configuration>
    //  </plugin>
    public void testException() {
        int i = 1;
        int j = 0;
        assertThatThrownBy(() -> {
            int result = i / j;
        } ).isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("by zero");
    }

    public static float invSqrt(float x) {
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x *= (1.5f - xhalf * x * x);
        return x;
    }

    @DisplayName("Exercice 4 page 111 - tester la methode invSqrt")
    @ParameterizedTest(name = "{index} - with x = ''{0}''")
    @ValueSource(floats = {0.0f, 5.0f, 0.1f, 1.2f, 10.10f})
    public void test_methode_invSqrt(float x) {
        assertThat(invSqrt(x)).isEqualTo(Math.sqrt(x));
    }

    @RepeatedTest(5)
    public void test_methode2_invSqrt() {
        Random r = new Random();
        float f = r.nextFloat()*1000;
        assertThat(invSqrt(f)).isEqualTo((float) Math.sqrt(f));
    }

}
