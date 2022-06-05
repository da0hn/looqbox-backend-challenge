package br.com.gabriel.looqbox.challenge.application.config;

import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonHighlighter;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlighterImpl;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonMergeSortImpl;
import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsByName;
import br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsHighlightedByName;
import br.com.gabriel.looqbox.challenge.core.ports.spi.PokemonRepository;
import br.com.gabriel.looqbox.challenge.core.usecase.impl.GetPokemonsByNameImpl;
import br.com.gabriel.looqbox.challenge.core.usecase.impl.GetPokemonsHighlightedByNameImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PokemonModuleConfig {


  @Bean
  public GetPokemonsByName getPokemonsByName(final PokemonRepository pokemonRepository, final PokemonSorter pokemonSorter) {
    return new GetPokemonsByNameImpl(pokemonRepository, pokemonSorter);
  }

  @Bean
  public PokemonHighlighter pokemonHighlighter() {
    return new PokemonHighlighterImpl();
  }

  @Bean
  public PokemonSorter pokemonSorter() {
    return new PokemonMergeSortImpl();
  }

  @Bean
  public GetPokemonsHighlightedByName getPokemonsHighlightedByName(
    final PokemonRepository pokemonRepository,
    final PokemonHighlighter pokemonHighlighter,
    final PokemonSorter pokemonSorter
  ) {
    return new GetPokemonsHighlightedByNameImpl(pokemonRepository, pokemonHighlighter, pokemonSorter);
  }
}
