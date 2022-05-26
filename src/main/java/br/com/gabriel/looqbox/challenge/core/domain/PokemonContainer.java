package br.com.gabriel.looqbox.challenge.core.domain;

import java.util.List;

public interface PokemonContainer {
  int halfSize();
  int size();
  Pokemon at(Integer position);
  void add(Pokemon pokemon);
  boolean hasPokemons();
  List<Pokemon> asList();
  PokemonContainer slice(int initialPosition, int endPosition);
}
