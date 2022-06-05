package br.com.gabriel.looqbox.challenge.core.ports.api;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlight;

import java.util.List;

public interface GetPokemonsHighlightedByName {

  Response execute(Request request);

  record Response(List<PokemonHighlight> pokemons) {}

  record Request(String highlightText) {}
}
