package br.com.gabriel.looqbox.challenge.core.domain;

class Counter {
  private int left;
  private int right;

  void incrementLeft() {
    this.left++;
  }

  void incrementRight() {
    this.right++;
  }

  int left() {
    return this.left;
  }

  int right() {
    return this.right;
  }
}
