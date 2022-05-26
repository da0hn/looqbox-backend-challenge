package br.com.gabriel.looqbox.challenge.data;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
  url = "{application.pokeapi.url}",
  name = "pokeApi"
)
public interface PokeApiFeignClient {


}
