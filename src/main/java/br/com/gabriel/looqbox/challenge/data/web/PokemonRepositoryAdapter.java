package br.com.gabriel.looqbox.challenge.data.web;

import br.com.gabriel.looqbox.challenge.core.PokemonRepository;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonContainer;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class PokemonRepositoryAdapter implements PokemonRepository {

  private static final Long LIMIT = 10_000L;
  private static final Long OFF_SET = 0L;
  private final PokeApiFeignClient pokeApiFeignClient;

  @Autowired
  public PokemonRepositoryAdapter(final PokeApiFeignClient pokeApiFeignClient) {
    this.pokeApiFeignClient = pokeApiFeignClient;
  }

  @Override public PokemonContainer fetchAllPokemons() {
    return this.pokeApiFeignClient.getAllPokemons(OFF_SET, LIMIT).results().stream()
      .map(pokemon -> new Pokemon(pokemon.name()))
      .collect(Collectors.collectingAndThen(Collectors.toList(), Pokemons::new));
  }
}
