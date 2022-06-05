package br.com.gabriel.looqbox.challenge.core.usecase.impl;

import br.com.gabriel.looqbox.challenge.core.ports.spi.PokemonRepository;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlighter;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsHighlightedByName;

import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GetPokemonsHighlightedByNameImpl implements GetPokemonsHighlightedByName {

  private final PokemonRepository repository;
  private final PokemonHighlighter pokemonHighlighter;
  private final PokemonSorter pokemonSorter;

  public GetPokemonsHighlightedByNameImpl(
    final PokemonRepository repository,
    final PokemonHighlighter pokemonHighlighter,
    final PokemonSorter pokemonSorter
  ) {
    this.repository = repository;
    this.pokemonHighlighter = pokemonHighlighter;
    this.pokemonSorter = pokemonSorter;
  }

  @Override public Response execute(final Request request) {

    final var pokemons = this.repository.fetchAllPokemons();

    final Predicate<Pokemon> findPokemonByPartialName = pokemon -> pokemon.name().contains(request.highlightText().toLowerCase(Locale.ROOT));
    final var filteredPokemons = pokemons.filter(findPokemonByPartialName);
    final var sortedPokemons = this.pokemonSorter.sort(filteredPokemons);

    return sortedPokemons.asList()
      .stream()
      .map(pokemon -> this.pokemonHighlighter.highlight(request.highlightText(), pokemon.name()))
      .filter(pokemon -> pokemon.highlight() != null)
      .collect(Collectors.collectingAndThen(Collectors.toList(), Response::new));
  }
}
