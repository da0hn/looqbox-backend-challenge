package br.com.gabriel.looqbox.challenge.core.usecase.impl;

import br.com.gabriel.looqbox.challenge.core.PokemonRepository;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.usecase.GetPokemonsByName;

import java.util.Locale;

public class GetPokemonsByNameImpl implements GetPokemonsByName {

  private final PokemonRepository repository;
  private final PokemonSorter sorter;

  public GetPokemonsByNameImpl(final PokemonRepository repository, final PokemonSorter sorter) {
    this.repository = repository;
    this.sorter = sorter;
  }

  @Override public Response execute(final Request request) {

    final var pokemons = this.repository.fetchAllPokemons();

    final var filteredPokemons = pokemons.filter(pokemon -> pokemon.name().contains(request.partialName().toLowerCase(Locale.ROOT)));

    final var sortedPokemons = this.sorter.sort(filteredPokemons);

    return new Response(sortedPokemons.asList());
  }
}
