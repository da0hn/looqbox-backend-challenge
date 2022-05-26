package br.com.gabriel.looqbox.challenge.data.web.response;

import java.util.List;

public record PokeApiResponse(
  Long count,
  Long next,
  Long previous,
  List<PokemonResponse> results
) {
}
