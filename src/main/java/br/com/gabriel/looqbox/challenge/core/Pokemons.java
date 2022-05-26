package br.com.gabriel.looqbox.challenge.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Pokemons implements PokemonContainer {

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

  @Override public boolean hasPokemons() {
    return this.pokemons.size() > 0;
  }

  @Override public List<Pokemon> asList() {
    return Collections.unmodifiableList(this.pokemons);
  }

  @Override public PokemonContainer slice(final int initialPosition, final int endPosition) {
    if(this.pokemons.size() == 1) throw new IllegalStateException("It was not possible slice the container content");
    return Pokemons.of(this.pokemons.subList(initialPosition, endPosition));
  }

  private static Pokemons of(final List<Pokemon> pokemons) {
    return new Pokemons(pokemons);
  }
}
