package br.com.gabriel.looqbox.challenge.core.domain;

import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonContainer;
import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonSorter;

import java.util.function.Supplier;

public class PokemonMergeSortImpl implements PokemonSorter {

  private static PokemonContainer merge(final PokemonContainer pokemonsAtLeft, final PokemonContainer pokemonsAtRight) {
    final var sortedPokemons = Pokemons.of();

    final var counter = new Counter();

    addInAscendingOrder(pokemonsAtLeft, pokemonsAtRight, sortedPokemons, counter);
    addRemainPokemons(pokemonsAtLeft, sortedPokemons, counter::left);
    addRemainPokemons(pokemonsAtRight, sortedPokemons, counter::right);

    return sortedPokemons;
  }

  private static void addInAscendingOrder(
    final PokemonContainer pokemonsAtLeft,
    final PokemonContainer pokemonsAtRight,
    final PokemonContainer sortedPokemons,
    final Counter counter
  ) {
    while(counter.left() < pokemonsAtLeft.size() && counter.right() < pokemonsAtRight.size()) {
      final var pokemonAtLeft = pokemonsAtLeft.at(counter.left());
      final var pokemonAtRight = pokemonsAtRight.at(counter.right());

      if(pokemonAtLeft.compareTo(pokemonAtRight) < 0) {
        sortedPokemons.add(pokemonAtLeft);
        counter.incrementLeft();
      }
      else {
        sortedPokemons.add(pokemonAtRight);
        counter.incrementRight();
      }
    }
  }

  private static void addRemainPokemons(
    final PokemonContainer pokemons,
    final PokemonContainer sortedPokemons,
    final Supplier<Integer> counterSupplier
  ) {
    int counter = counterSupplier.get();
    while(counter < pokemons.size()) {
      sortedPokemons.add(pokemons.at(counter++));
    }
  }

  @Override public PokemonContainer sort(final PokemonContainer pokemons) {
    if(pokemons.size() < 2) return pokemons;
    final int halfSize = pokemons.halfSize();
    final var pokemonsAtLeft = this.sort(pokemons.slice(0, halfSize));
    final var pokemonsAtRight = this.sort(pokemons.slice(halfSize, pokemons.size()));
    return merge(pokemonsAtLeft, pokemonsAtRight);
  }

  private static class Counter {
    private int left;
    private int right;

    void incrementLeft() {
      this.left++;
    }

    void incrementRight() {
      this.right++;
    }

    int left() {
      return this.left;
    }

    int right() {
      return this.right;
    }
  }
}
