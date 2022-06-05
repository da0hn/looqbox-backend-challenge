package br.com.gabriel.looqbox.challenge.core.ports.api;

import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;

import java.util.Collection;

public interface GetPokemonsByName {

  Response execute(Request request);

  record Request(String partialName) {}

  record Response(Collection<Pokemon> pokemons) {}
}
