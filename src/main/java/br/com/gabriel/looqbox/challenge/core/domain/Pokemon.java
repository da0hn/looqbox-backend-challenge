package br.com.gabriel.looqbox.challenge.core.domain;

public record Pokemon(
  String name
) implements Comparable<Pokemon> {
  @Override public int compareTo(final Pokemon other) {
    if(other == null || other.name == null) return -1;

    final var comparedLength = this.name.length() - other.name.length();

    if(comparedLength != 0) return comparedLength;

    return this.name.compareTo(other.name);
  }
}
