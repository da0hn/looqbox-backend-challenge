package br.com.gabriel.looqbox.challenge.core.usecase.impl;

import br.com.gabriel.looqbox.challenge.core.PokemonRepository;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonHighlighter;
import br.com.gabriel.looqbox.challenge.core.domain.PokemonSorter;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemons;
import br.com.gabriel.looqbox.challenge.core.usecase.GetPokemonsHighlightedByName;
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
  private PokemonSorter pokemonSorter;
  @Mock
  private PokemonHighlighter pokemonHighlighter;

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
    doReturn("").when(this.pokemonHighlighter).highlight(any(), any());

    this.getPokemonsHighlightedByName.execute(new GetPokemonsHighlightedByName.Request("pid", "pid"));

    verify(this.pokemonSorter, times(1)).sort(any());
    verify(this.pokemonHighlighter, times(3)).highlight(any(), any());
    verify(this.pokemonRepository, times(1)).fetchAllPokemons();
  }


}
