package br.com.gabriel.looqbox.challenge.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("unit")
@DisplayName("Test compareTo method of a pokemon")
class PokemonCompareToTest {

  @Test
  @DisplayName("Should compare pokemon name length in ascending order")
  void test1() {
    final var pokemonA = new Pokemon("aaaa");
    final var pokemonB = new Pokemon("aaaaa");

    final var value = pokemonA.compareTo(pokemonB);

    assertTrue(value < 0);
  }

  @Test
  @DisplayName("Should compare pokemon name characters when has same name length")
  void test2() {
    final var pokemonA = new Pokemon("aaaaa");
    final var pokemonB = new Pokemon("aaaab");

    final var value = pokemonA.compareTo(pokemonB);

    assertTrue(value < 0);
  }

  @Test
  @DisplayName("Should return negative number when other pokemon has invalid data")
  void test3() {
    final var pokemonA = new Pokemon("aaaaa");
    final var pokemonB = new Pokemon(null);

    final var value = pokemonA.compareTo(pokemonB);

    assertTrue(value < 0);
  }
}
