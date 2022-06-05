package br.com.gabriel.looqbox.challenge.core.usecase.impl;

import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemons;
import br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsByName;
import br.com.gabriel.looqbox.challenge.core.ports.spi.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.gabriel.looqbox.challenge.core.ports.api.GetPokemonsByName.Request;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
@DisplayName("Test of query pokemon by name")
class GetPokemonsByNameImplTest {


  private GetPokemonsByName getPokemonsByName;
  @Mock
  private PokemonRepository repository;

  @BeforeEach
  void setUp() {
    this.getPokemonsByName = new GetPokemonsByNameImpl(this.repository);
  }

  @Test
  @DisplayName("Should fetch pokemons with partial name in ascending order")
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

    doReturn(unsortedPokemons).when(this.repository).fetchAllPokemons();

    this.getPokemonsByName.execute(new Request("pi"));

    verify(this.repository, times(1)).fetchAllPokemons();

  }

}
