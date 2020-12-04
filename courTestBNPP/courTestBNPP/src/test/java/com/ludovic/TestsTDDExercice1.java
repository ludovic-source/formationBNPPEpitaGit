package com.ludovic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestsTDDExercice1 {

    FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void verifier_conversion_nbre_entier_en_string() throws FizzBuzzNegatifException {
        int nbre = 2;
        String nbreString = fizzBuzz.fizzBuzz(nbre);
        assertThat(nbreString).isInstanceOf(String.class);
        assertThat(nbreString).isEqualTo("2");
    }

    @Test
    public void verifier_si_multiple_3_retourne_FIZZ() throws FizzBuzzNegatifException {
        int nbre = 6;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZ");
    }

    @Test
    public void verifier_si_multiple_5_retourne_BUZZ() throws FizzBuzzNegatifException {
        int nbre = 5;
        // assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("BUZZ"); // avant exercice 1.7
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("BUZZBUZZ"); // après exercice 1.7
    }

    @Test
    public void verifier_si_multiple_3_et_5_retourne_FIZZBUZZ() throws FizzBuzzNegatifException {
        int nbre = 15;
        // assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZBUZZ"); // avant exercice 1.7
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZBUZZBUZZ"); // après exercice 1.7
    }

    @Test
    public void verifier_si_exception_quand_nbre_negatif() {
        int nbre = -2;
        assertThatThrownBy(() -> {
            fizzBuzz.fizzBuzz(nbre);
        } ).isInstanceOf(FizzBuzzNegatifException.class)
                .hasMessageContaining("No negative numbers allowed");
    }

    @Test
    public void verifier_si_multiple_7_retourne_QIX() throws FizzBuzzNegatifException {
        int nbre = 14;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("QIX");
        nbre = 21;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZQIX");
        nbre = 105;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZBUZZQIX*BUZZ");
        nbre = 35;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("BUZZQIXFIZZBUZZ");
        nbre = 15;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZBUZZBUZZ");
        nbre = 33;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZFIZZFIZZ");
    }

    @Test
    public void verifier_si_aucun_zero() throws FizzBuzzNegatifException {
        int nbre = 1003;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("1**3");
        nbre = 101;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("1*1");
        nbre = 303;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZFIZZ*FIZZ");
        nbre = 105;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZBUZZQIX*BUZZ");
        nbre = 10101;
        assertThat(fizzBuzz.fizzBuzz(nbre)).isEqualTo("FIZZQIX**");

    }

}
