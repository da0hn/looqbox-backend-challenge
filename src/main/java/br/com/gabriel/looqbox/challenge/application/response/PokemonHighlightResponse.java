package br.com.gabriel.looqbox.challenge.application.response;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlight;

import java.util.List;

public record PokemonHighlightResponse(
  List<PokemonHighlight> result
) {
}
