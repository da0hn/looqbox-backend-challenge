package br.com.gabriel.looqbox.challenge.core.domain;

import java.util.List;
import java.util.function.Predicate;

public interface PokemonContainer {
  int halfSize();
  int size();
  Pokemon at(Integer position);
  void add(Pokemon pokemon);
  List<Pokemon> asList();
  PokemonContainer slice(int initialPosition, int endPosition);
  PokemonContainer filter(Predicate<? super Pokemon> filter);
  PokemonContainer sort(PokemonSorter sorter);
}
