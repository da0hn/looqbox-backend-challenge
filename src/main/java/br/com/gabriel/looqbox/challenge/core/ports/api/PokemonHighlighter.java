package br.com.gabriel.looqbox.challenge.core.ports.api;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlight;

public interface PokemonHighlighter {

  PokemonHighlight highlight(String text, String name);

}
