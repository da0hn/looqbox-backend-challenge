package br.com.gabriel.looqbox.challenge.core.ports.spi;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonContainer;

public interface PokemonRepository {

  PokemonContainer fetchAllPokemons();

}
