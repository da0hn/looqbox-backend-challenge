package br.com.gabriel.looqbox.challenge.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import static java.util.Arrays.asList;

@Tag("unit")
@DisplayName("Pokemons container implementation test")
class PokemonsTest {

  private static final Pokemon PIKACHU = new Pokemon("pikachu");
  private static final Pokemon SQUIRTLE = new Pokemon("squirtle");
  private static final Pokemon RATICATE = new Pokemon("raticate");
  private static final Pokemon SANDSLASH = new Pokemon("sandslash");

  @Nested
  @DisplayName("Test size and halfSize method")
  class Size {

    @Test
    @DisplayName("Should return quantity of pokemons")
    void test1() {
      final var pokemons = Pokemons.of(PIKACHU, SQUIRTLE);
      assertEquals(2, pokemons.size());
    }

    @Test
    @DisplayName("Should return 0 when hasn't pokemons")
    void test2() {
      final var pokemons = Pokemons.of();
      assertEquals(0, pokemons.size());
    }

    @Test
    @DisplayName("Should return half of size")
    void test3() {
      final var pokemons = Pokemons.of(PIKACHU, SQUIRTLE);
      assertEquals(1, pokemons.halfSize());
    }
  }

  @Nested
  @DisplayName("Test hasPokemons method")
  class HasPokemons {
    @Test
    @DisplayName("Should return true when has pokemons")
    void test1() {
      final var pokemons = Pokemons.of(PIKACHU);
      assertEquals(1, pokemons.size());
    }

    @Test
    @DisplayName("Should return false when hasn't pokemons")
    void test2() {
      final var pokemons = Pokemons.of(PIKACHU);
      assertEquals(1, pokemons.size());
    }
  }

  @Nested
  @DisplayName("Test pokemon container creation")
  class Create {
    @Test
    @DisplayName("Should create an modifiable container")
    void test1() {
      final var pokemons = Pokemons.of();
      pokemons.add(PIKACHU);
      assertEquals(1, pokemons.size());
    }

    @Test
    @DisplayName("Should statically create with elements")
    void test2() {
      final var pokemons = Pokemons.of(PIKACHU);
      assertEquals(1, pokemons.size());
    }

    @Test
    @DisplayName("Should create using constructor")
    void test3() {
      final var pokemons = new Pokemons(new ArrayList<>());
      pokemons.add(PIKACHU);
      assertEquals(1, pokemons.size());
    }
  }

  @Nested
  @DisplayName("Test add method")
  class Add {

    @Test
    @DisplayName("Should add an pokemon")
    void test1() {
      final var pokemons = Pokemons.of(SQUIRTLE);
      pokemons.add(PIKACHU);
      assertEquals(2, pokemons.size());
    }

    @Test
    @DisplayName("Should not add a null pokemon")
    void test2() {
      final var pokemons = Pokemons.of(SQUIRTLE);
      assertThrows(NullPointerException.class, () -> pokemons.add(null));
    }
  }

  @Nested
  @DisplayName("Test slice method")
  class Slice {

    @Test
    @DisplayName("Should slice a existing pokemon container")
    void test1() {
      final var pokemons = Pokemons.of(SQUIRTLE, PIKACHU, RATICATE, SANDSLASH);
      final var slicedPokemons = pokemons.slice(0, 2);
      assertEquals(2, slicedPokemons.size());
    }

    @Test
    @DisplayName("Should not slice a container with one pokemon")
    void test2() {
      final var pokemons = Pokemons.of(SQUIRTLE);
      Assertions.assertThrows(
        IllegalStateException.class,
        () -> pokemons.slice(0, 2),
        "It was not possible slice the container content"
      );
    }
  }

  @Nested
  @DisplayName("Test at method")
  class At {

    @Test
    @DisplayName("Should get a pokemon by position")
    void test1() {
      final var pokemons = Pokemons.of(SQUIRTLE);
      final var pokemon = pokemons.at(0);
      assertEquals(SQUIRTLE, pokemon);
    }

    @Test
    @DisplayName("Should not get a pokemon when position is out of bound")
    void test2() {
      final var pokemons = Pokemons.of(SQUIRTLE);
      Assertions.assertThrows(IllegalArgumentException.class, () -> pokemons.at(3), "Out of bound");
    }
  }

  @Nested
  @DisplayName("Test asList method")
  class AsList {

    @Test
    @DisplayName("Should create unmodifiable list")
    void test1() {
      final var pokemons = Pokemons.of(PIKACHU, SQUIRTLE);
      final var pokemonList = pokemons.asList();
      Assertions.assertThrows(UnsupportedOperationException.class, () -> pokemonList.add(RATICATE));
    }

    @Test
    @DisplayName("Should create an list with same elements")
    void test2() {
      final var pokemons = Pokemons.of(PIKACHU, SQUIRTLE);
      final var pokemonList = pokemons.asList();
      assertEquals(pokemonList, asList(PIKACHU, SQUIRTLE));
    }

  }

}
