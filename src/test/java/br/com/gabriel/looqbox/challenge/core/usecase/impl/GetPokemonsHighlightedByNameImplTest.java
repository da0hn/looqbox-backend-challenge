package br.com.gabriel.looqbox.challenge.core.usecase.impl;

import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlight;
import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonHighlighter;
import br.com.gabriel.looqbox.challenge.core.ports.api.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemons;
import br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsHighlightedByName;
import br.com.gabriel.looqbox.challenge.core.ports.spi.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
@DisplayName("Test get highlighted pokemons by name")
class GetPokemonsHighlightedByNameImplTest {


  private GetPokemonsHighlightedByName getPokemonsHighlightedByName;
  @Mock
  private PokemonRepository pokemonRepository;
  @Mock
  private PokemonHighlighter pokemonHighlighter;
  @Mock
  private PokemonSorter pokemonSorter;

  @BeforeEach
  void setUp() {
    this.getPokemonsHighlightedByName = new GetPokemonsHighlightedByNameImpl(
      this.pokemonRepository,
      this.pokemonHighlighter,
      this.pokemonSorter
    );
  }

  @Test
  @DisplayName("Should get highlighted name of pokemons by partial name")
  void test1() {
    final var unsortedPokemons = Pokemons.of(
      new Pokemon("pidgeotto"),
      new Pokemon("pidgey"),
      new Pokemon("pidgeot")
    );
    final var sortedPokemons = Pokemons.of(
      new Pokemon("pidgeot"),
      new Pokemon("pidgeotto"),
      new Pokemon("pidgey")
    );


    doReturn(unsortedPokemons).when(this.pokemonRepository).fetchAllPokemons();
    doReturn(sortedPokemons).when(this.pokemonSorter).sort(any());
    doReturn(new PokemonHighlight("", "")).when(this.pokemonHighlighter).highlight(any(), any());

    this.getPokemonsHighlightedByName.execute(new GetPokemonsHighlightedByName.Request("pid"));

    verify(this.pokemonSorter, times(1)).sort(any());
    verify(this.pokemonHighlighter, times(3)).highlight(any(), any());
    verify(this.pokemonRepository, times(1)).fetchAllPokemons();
  }

  @Test
  @DisplayName("Should only highlight non null pokemons")
  void test2() {
    final var unsortedPokemons = Pokemons.of(
      new Pokemon("pidgeotto"),
      new Pokemon("pidgey"),
      new Pokemon("pidgeot")
    );
    final var sortedPokemons = Pokemons.of(
      new Pokemon("pidgeot"),
      new Pokemon("pidgeotto"),
      new Pokemon("pidgey")
    );


    doReturn(unsortedPokemons).when(this.pokemonRepository).fetchAllPokemons();
    doReturn(sortedPokemons).when(this.pokemonSorter).sort(any());
    doReturn(new PokemonHighlight("", null)).when(this.pokemonHighlighter).highlight(any(), any());

    this.getPokemonsHighlightedByName.execute(new GetPokemonsHighlightedByName.Request("pid"));

    verify(this.pokemonSorter, times(1)).sort(any());
    verify(this.pokemonHighlighter, times(3)).highlight(any(), any());
    verify(this.pokemonRepository, times(1)).fetchAllPokemons();
  }
}
