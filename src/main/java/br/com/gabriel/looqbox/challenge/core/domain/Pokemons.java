package br.com.gabriel.looqbox.challenge.core.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Pokemons implements PokemonContainer, PokemonSorter {

  private final List<Pokemon> pokemons;

  public Pokemons(final List<Pokemon> pokemons) {
    this.pokemons = Optional.ofNullable(pokemons).orElse(new LinkedList<>());
  }

  public static Pokemons of(final Pokemon... pokemons) {
    if(pokemons.length == 0) return new Pokemons(new LinkedList<>());
    return new Pokemons(new LinkedList<>(List.of(pokemons)));
  }

  @Override public int halfSize() {
    return Math.floorDiv(this.pokemons.size(), 2);
  }

  @Override public int size() {
    return this.pokemons.size();
  }

  @Override public Pokemon at(final Integer position) {
    Objects.requireNonNull(position);
    if(position < 0 || position > this.size()) throw new IllegalArgumentException("Out of bound");
    return this.pokemons.get(position);
  }

  @Override public void add(final Pokemon pokemon) {
    Objects.requireNonNull(pokemon);
    this.pokemons.add(pokemon);
  }

  @Override public List<Pokemon> asList() {
    return Collections.unmodifiableList(this.pokemons);
  }

  @Override public PokemonContainer slice(final int initialPosition, final int endPosition) {
    if(this.pokemons.size() == 1) throw new IllegalStateException("It was not possible slice the container content");
    return Pokemons.of(this.pokemons.subList(initialPosition, endPosition));
  }

  @Override public PokemonContainer filter(final Predicate<? super Pokemon> predicate) {
    return this.pokemons.stream()
      .filter(predicate)
      .collect(Collectors.collectingAndThen(Collectors.toList(), Pokemons::of));
  }

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

  @Override public PokemonContainer sort() {
    return this.sort(this);
  }

  private PokemonContainer sort(final PokemonContainer pokemons) {
    if(pokemons.size() < 2) return pokemons;
    final int halfSize = pokemons.halfSize();
    final var pokemonsAtLeft = this.sort(pokemons.slice(0, halfSize));
    final var pokemonsAtRight = this.sort(pokemons.slice(halfSize, pokemons.size()));
    return merge(pokemonsAtLeft, pokemonsAtRight);
  }

  private static Pokemons of(final List<Pokemon> pokemons) {
    return new Pokemons(pokemons);
  }
}
