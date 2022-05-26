package br.com.gabriel.looqbox.challenge.core;

import java.util.Collection;

public interface GetPokemonsByName {

  Response execute(Request request);

  record Request(String partialName) {}

  record Response(Collection<Pokemon> pokemons) {}
}
