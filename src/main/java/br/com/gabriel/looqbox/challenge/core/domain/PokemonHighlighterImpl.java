package br.com.gabriel.looqbox.challenge.core.domain;

import java.util.Locale;

public class PokemonHighlighterImpl implements PokemonHighlighter {

  private static final String OPEN_PRE_TAG = "<pre>";
  private static final String CLOSE_PRE_TAG = "</pre>";


  @Override public PokemonHighlight highlight(final String text, final String name) {

    ifTextIsInvalidThrowException(text);
    ifNameIsNullThrowException(name);

    if(pokemonNameIsNotContainedInText(text, name)) return new PokemonHighlight(name, null);

    final StringBuilder highlightBuilder = buildPokemonHighlight(text, name);

    return new PokemonHighlight(name, highlightBuilder.toString());
  }

  private static StringBuilder buildPokemonHighlight(final String text, final String name) {
    final var highlightBuilder = new StringBuilder();
    final var index = name.indexOf(text);
    int counter = 0;
    while(counter < name.length()) {
      if(isInitialOfHighlight(index, counter)) {
        applyHighlight(text, highlightBuilder);
        counter += text.length();
        continue;
      }
      highlightBuilder.append(name.charAt(counter));
      counter++;
    }
    return highlightBuilder;
  }

  private static void applyHighlight(final String text, final StringBuilder highlightBuilder) {
    highlightBuilder.append(OPEN_PRE_TAG)
      .append(text)
      .append(CLOSE_PRE_TAG);
  }

  private static boolean isInitialOfHighlight(final int index, final int counter) {
    return index == counter;
  }

  private static boolean pokemonNameIsNotContainedInText(final String text, final String name) {
    return !name.contains(text.toLowerCase(Locale.ROOT));
  }

  private static void ifNameIsNullThrowException(final String name) {
    if(name == null) throw new IllegalArgumentException("The pokemon name must be informed");
  }

  private static void ifTextIsInvalidThrowException(final CharSequence text) {
    if(text == null || text.isEmpty()) throw new IllegalArgumentException("The highlighted text must be informed");
  }

}
