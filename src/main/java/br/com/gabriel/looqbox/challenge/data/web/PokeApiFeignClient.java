package br.com.gabriel.looqbox.challenge.data.web;

import br.com.gabriel.looqbox.challenge.data.web.response.PokeApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
  name = "pokeApi",
  url = "${application.pokeapi.url}"
)
public interface PokeApiFeignClient {

  @GetMapping("/pokemon")
  PokeApiResponse getAllPokemons(
    @RequestParam("offset") Long offset,
    @RequestParam("limit") Long limit
  );

}
