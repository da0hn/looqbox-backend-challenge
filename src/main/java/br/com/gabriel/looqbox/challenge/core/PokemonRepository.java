package br.com.gabriel.looqbox.challenge.core;

import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;

import java.util.Collection;

public interface PokemonRepository {

  Collection<Pokemon> fetchAllPokemons();

}
