package br.com.gabriel.looqbox.challenge.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Tag("unit")
@DisplayName("Test pokemon highlighter")
class PokemonHighlighterTest {


  private static final String PIKACHU = "pikachu";
  private PokemonHighlighter pokemonHighlighter;

  @BeforeEach
  void setUp() {
    this.pokemonHighlighter = new PokemonHighlighterImpl();
  }

  @Test
  @DisplayName("Should highlight pokemon name when partial text is on begining")
  void test1() {
    final var text = "pi";
    final String HIGHLIGHT = "<pre>pi</pre>kachu";
    final var pokemonHighlighted = this.pokemonHighlighter.highlight(text, PIKACHU);
    assertEquals(HIGHLIGHT, pokemonHighlighted);
  }

  @Test
  @DisplayName("Should not highlight when not found the partial text")
  void test2() {
    final var text = "char";
    final var pokemonHighlighted = this.pokemonHighlighter.highlight(text, PIKACHU);
    assertNull(pokemonHighlighted);
  }

  @Test
  @DisplayName("Should highlight pokemon name when partial text is on middle")
  void test3() {
    final var text = "ka";
    final var HIGHLIGHT = "pi<pre>ka</pre>chu";
    final var pokemonHighlighted = this.pokemonHighlighter.highlight(text, PIKACHU);
    assertEquals(HIGHLIGHT, pokemonHighlighted);
  }

  @Test
  @DisplayName("Should highlight pokemon name when partial text is in end")
  void test4() {
    final var text = "hu";
    final var HIGHLIGHT = "pikac<pre>hu</pre>";
    final var pokemonHighlighted = this.pokemonHighlighter.highlight(text, PIKACHU);
    assertEquals(HIGHLIGHT, pokemonHighlighted);
  }

  @Test
  @DisplayName("Should highlight all pokemon name when partial text is the name")
  void test5() {
    final var text = "pikachu";
    final var HIGHLIGHT = "<pre>pikachu</pre>";
    final var pokemonHighlighted = this.pokemonHighlighter.highlight(text, PIKACHU);
    assertEquals(HIGHLIGHT, pokemonHighlighted);
  }

  @Test
  @DisplayName("Should not highlight when text is null")
  void test6() {
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> this.pokemonHighlighter.highlight(null, PIKACHU),
      "The highlighted text must be informed"
    );
  }

  @Test
  @DisplayName("Should not highlight when text is empty")
  void test7() {
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> this.pokemonHighlighter.highlight("", PIKACHU),
      "The highlighted text must be informed"
    );
  }

  @Test
  @DisplayName("Should not highlight when pokemon is null")
  void test8() {
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> this.pokemonHighlighter.highlight("", PIKACHU),
      "The pokemon name must be informed"
    );
  }
}
