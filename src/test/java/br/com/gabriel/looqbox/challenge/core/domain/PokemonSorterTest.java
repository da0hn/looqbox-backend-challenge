package br.com.gabriel.looqbox.challenge.core.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@DisplayName("Test Pokemon sorter")
class PokemonSorterTest {

  private static final Pokemon PIKACHU = new Pokemon("pikachu");
  private static final Pokemon IVYSAUR = new Pokemon("ivysaur");
  private static final Pokemon SQUIRTLE = new Pokemon("squirtle");
  private static final Pokemon RATICATE = new Pokemon("raticate");
  private static final Pokemon SANDSLASH = new Pokemon("sandslash");
  private Pokemons unsortedPokemons;
//  private PokemonMergeSortImpl pokemonSorter;

  @BeforeEach
  void setUp() {
//    this.pokemonSorter = new PokemonMergeSortImpl();
  }

  @Test
  @DisplayName("Should sort a unsorted pokemons list in alphabetical order")
  void test1() {
    this.unsortedPokemons = Pokemons.of(
      PIKACHU,
      IVYSAUR,
      SQUIRTLE,
      RATICATE,
      SANDSLASH
    );
    final var sortedPokemons = this.unsortedPokemons.sort();
    assertThat(sortedPokemons.asList()).containsSequence(IVYSAUR, PIKACHU, RATICATE, SANDSLASH, SQUIRTLE);
  }

  @Test
  @DisplayName("Should sort a pre-sorted pokemons list in alphabetical order")
  void test2() {
    this.unsortedPokemons = Pokemons.of(
      PIKACHU,
      RATICATE,
      SANDSLASH,
      SQUIRTLE,
      IVYSAUR
    );
    final var sortedPokemons = this.unsortedPokemons.sort();
    assertThat(sortedPokemons.asList()).containsSequence(IVYSAUR, PIKACHU, RATICATE, SANDSLASH, SQUIRTLE);
  }


  @Test
  @DisplayName("Should sort an reverse sorted pokemons list in alphabetical order")
  void test3() {
    this.unsortedPokemons = Pokemons.of(
      SQUIRTLE,
      SANDSLASH,
      RATICATE,
      PIKACHU,
      IVYSAUR
    );
    final var sortedPokemons = this.unsortedPokemons.sort();
    assertThat(sortedPokemons.asList()).containsSequence(IVYSAUR, PIKACHU, RATICATE, SANDSLASH, SQUIRTLE);
  }


  @Test
  @DisplayName("Should not sort a pokemons list with one element")
  void test4() {
    this.unsortedPokemons = Pokemons.of(
      SQUIRTLE
    );
    final var sortedPokemons = this.unsortedPokemons.sort();
    assertThat(sortedPokemons.asList()).containsSequence(SQUIRTLE);
  }
}
