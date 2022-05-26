package br.com.gabriel.looqbox.challenge.application.response;

import java.util.Collection;

public record PokemonResponse(
  Collection<String> result
) {
}
