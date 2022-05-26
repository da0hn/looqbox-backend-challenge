package br.com.gabriel.looqbox.challenge.core;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonContainer;

public interface PokemonRepository {

  PokemonContainer fetchAllPokemons();

}
