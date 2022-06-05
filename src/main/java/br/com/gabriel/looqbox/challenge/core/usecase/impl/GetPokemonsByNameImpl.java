package br.com.gabriel.looqbox.challenge.core.usecase.impl;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsByName;
import br.com.gabriel.looqbox.challenge.core.ports.spi.PokemonRepository;

import java.util.Locale;

public class GetPokemonsByNameImpl implements GetPokemonsByName {

  private final PokemonRepository repository;

  public GetPokemonsByNameImpl(final PokemonRepository repository) {
    this.repository = repository;
  }

  @Override public Response execute(final Request request) {

    final var pokemons = this.repository.fetchAllPokemons();

    final var filteredPokemons = pokemons.filter(pokemon -> pokemon.name().contains(request.partialName().toLowerCase(Locale.ROOT)));

    return new Response(((PokemonSorter) filteredPokemons).sort().asList());
  }
}
