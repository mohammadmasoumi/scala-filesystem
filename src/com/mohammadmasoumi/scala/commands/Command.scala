package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.filesystem.State

/*
  Transform an state to another state
 */
trait Command {

  def apply(state: State): State

}

object Command {

  val MKDIR = "mkdir"
  val LS = "ls"

  def emptyCommand: Command = new Command { // anonymous class
    override def apply(state: State): State = state
  }

  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(name + ": incomplete command!")
  }

  /*
    factory method
   */
  def from(input: String): Command = {
    val tokens = input.split(" ") // array of string

    if (input.isEmpty || tokens.isEmpty) emptyCommand
    else if (MKDIR.equals(tokens(0)))
      if (tokens.length < 2) incompleteCommand("mkdir")
      else new Mkdir(tokens(1))
    else if (LS.equals(tokens(0)))
      new Ls
    else new UnknownCommand
  }

}