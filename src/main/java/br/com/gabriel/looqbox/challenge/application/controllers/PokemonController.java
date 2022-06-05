package br.com.gabriel.looqbox.challenge.application.controllers;

import br.com.gabriel.looqbox.challenge.application.response.PokemonHighlightResponse;
import br.com.gabriel.looqbox.challenge.application.response.PokemonResponse;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.usecase.GetPokemonsByName;
import br.com.gabriel.looqbox.challenge.core.usecase.GetPokemonsHighlightedByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

  private final GetPokemonsByName getPokemonsByName;
  private final GetPokemonsHighlightedByName getPokemonsHighlightedByName;

  @Autowired
  public PokemonController(
    final GetPokemonsByName getPokemonsByName,
    final GetPokemonsHighlightedByName getPokemonsHighlightedByName
  ) {
    this.getPokemonsByName = getPokemonsByName;
    this.getPokemonsHighlightedByName = getPokemonsHighlightedByName;
  }


  @GetMapping
  public ResponseEntity<PokemonResponse> getPokemonsByPartialName(@RequestParam("name") final String partialName) {
    final var response = this.getPokemonsByName.execute(new GetPokemonsByName.Request(partialName));

    final var mappedResponse = response.pokemons().stream()
      .map(Pokemon::name)
      .collect(Collectors.collectingAndThen(Collectors.toList(), PokemonResponse::new));

    return ResponseEntity.ok(mappedResponse);
  }

  @GetMapping("/highlight")
  public ResponseEntity<PokemonHighlightResponse> getPokemonsHighlightedByName(
    @RequestParam("partialText") final String partialText
  ) {
    final var response = this.getPokemonsHighlightedByName.execute(new GetPokemonsHighlightedByName.Request(
      partialText
    ));
    return ResponseEntity.ok(new PokemonHighlightResponse(response.pokemons()));
  }

}
