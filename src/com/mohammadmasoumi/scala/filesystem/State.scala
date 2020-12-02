package com.mohammadmasoumi.scala.filesystem

import com.mohammadmasoumi.scala.files.Directory

/*
  output: output or previous command
 */
class State(val root: Directory, val wd: Directory, val output: String) {

  def show: Unit = {
    println(output)
    print(State.SHELL_TOKEN)
  }

  def setMessage(message: String): State =
    State(root, wd, message) // call apply method
}

object State {
  val SHELL_TOKEN = "$ "

  // factory method
  def apply(root: Directory, wd: Directory, output: String = "") =
    new State(root, wd, output)


}