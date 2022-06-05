package br.com.gabriel.looqbox.challenge.application.config;

import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlighter;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlighterImpl;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonSorter;
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
  public GetPokemonsByName getPokemonsByName(final PokemonRepository pokemonRepository) {
    return new GetPokemonsByNameImpl(pokemonRepository);
  }

  @Bean
  public PokemonHighlighter pokemonHighlighter() {
    return new PokemonHighlighterImpl();
  }

  @Bean
  public GetPokemonsHighlightedByName getPokemonsHighlightedByName(
    final PokemonRepository pokemonRepository,
    final PokemonHighlighter pokemonHighlighter
  ) {
    return new GetPokemonsHighlightedByNameImpl(pokemonRepository, pokemonHighlighter);
  }
}
