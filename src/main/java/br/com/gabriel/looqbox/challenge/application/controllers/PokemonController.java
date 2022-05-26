package br.com.gabriel.looqbox.challenge.application.controllers;

import br.com.gabriel.looqbox.challenge.application.response.PokemonResponse;
import br.com.gabriel.looqbox.challenge.core.domain.Pokemon;
import br.com.gabriel.looqbox.challenge.core.usecase.GetPokemonsByName;
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

  @Autowired
  public PokemonController(final GetPokemonsByName getPokemonsByName) {
    this.getPokemonsByName = getPokemonsByName;
  }


  @GetMapping
  public ResponseEntity<PokemonResponse> getPokemonsByPartialName(@RequestParam("name") final String partialName) {
    final var response = this.getPokemonsByName.execute(new GetPokemonsByName.Request(partialName));

    final var mappedResponse = response.pokemons().stream()
      .map(Pokemon::name)
      .collect(Collectors.collectingAndThen(Collectors.toList(), PokemonResponse::new));

    return ResponseEntity.ok(mappedResponse);
  }


}
