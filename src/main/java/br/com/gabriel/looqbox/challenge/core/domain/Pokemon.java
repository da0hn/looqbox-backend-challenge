package br.com.gabriel.looqbox.challenge.core.domain;

public record Pokemon(
  String name
) implements Comparable<Pokemon> {
  @Override public int compareTo(final Pokemon other) {
    if(other == null) return 1;
    return this.name.compareTo(other.name);
  }
}
