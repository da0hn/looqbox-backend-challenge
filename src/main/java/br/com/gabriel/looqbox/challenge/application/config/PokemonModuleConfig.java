package br.com.gabriel.looqbox.challenge.application.config;

import br.com.gabriel.looqbox.challenge.core.PokemonRepository;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonMergeSortImpl;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.usecase.GetPokemonsByName;
import br.com.gabriel.looqbox.challenge.core.usecase.impl.GetPokemonsByNameImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonModuleConfig {


  @Bean
  public GetPokemonsByName getPokemonsByName(final PokemonRepository pokemonRepository, final PokemonSorter pokemonSorter) {
    return new GetPokemonsByNameImpl(pokemonRepository, pokemonSorter);
  }

  @Bean
  public PokemonSorter pokemonSorter() {
    return new PokemonMergeSortImpl();
  }
}