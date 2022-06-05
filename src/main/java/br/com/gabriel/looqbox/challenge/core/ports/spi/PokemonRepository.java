package br.com.gabriel.looqbox.challenge.core.ports.spi;

import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonContainer;

public interface PokemonRepository {

  PokemonContainer fetchAllPokemons();

}
