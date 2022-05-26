package br.com.gabriel.looqbox.challenge.core;

import java.util.Collection;

public interface PokemonRepository {

  Collection<Pokemon> fetchAllPokemons();

}
