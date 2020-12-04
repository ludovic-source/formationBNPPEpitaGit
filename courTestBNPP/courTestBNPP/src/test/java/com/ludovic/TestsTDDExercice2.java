package com.ludovic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestsTDDExercice2 {

    private StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void verifier_methode_add_avec_string_en_entree_2_nbres_max() {
        String valeurATester = "1";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("1");
        valeurATester = "1,2";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("3");
        valeurATester = "";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("0");
    }

    @Test
    public void verifier_que_methode_add_renvoie_un_string() {
        String valeurATester = "1";
        assertThat(stringCalculator.add(valeurATester)).isInstanceOf(String.class);
    }

    @ParameterizedTest(name = "{index} - with x = {0}")
    @ValueSource(strings = {"6,15,21,25,10,9"})
    public void verifier_methode_add_avec_string_en_entree_nbres_illimités(String x) {
        assertThat(stringCalculator.add(x)).isEqualTo("86");
    }

    @Test
    public void verifier_que_la_methode_accepte_caractere_fin_de_ligne_comme_separateur() {
        String valeurATester = "5,5\n6,4";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("20");
    }

    @Test
    public void verifier_si_entree_methode_add_valide() {
        String valeurATester = "175.2,\n35";
        assertThatThrownBy(() -> {
            stringCalculator.add(valeurATester);
        } ).isInstanceOf(EntryInvalidException.class)
                .hasMessageContaining("Number expected but ‘\n’ found at position 6");
    }

    @Test
    public void verifier_si_pas_de_separateur_en_fin_de_chaine() {
        String valeurATester = "1,3,";
        assertThatThrownBy(() -> {
            stringCalculator.add(valeurATester);
        }).isInstanceOf(EntryInvalidException.class)
                .hasMessageContaining("Number expected but EOF found");
    }

    @Test
    public void verifier_fonctionnement_avec_separateur_personnalise() {
        String valeurATester = "//;\n1;2";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("3");
        valeurATester = "//sep\n2sep3";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("5");
        // devant | il faut mettre l'échappement \\
        valeurATester = "//\\|\n1|2|3";
        assertThat(stringCalculator.add(valeurATester)).isEqualTo("6");
    }

}
