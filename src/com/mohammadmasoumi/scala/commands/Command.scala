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
  val PWD = "pwd"
  val TOUCH = "touch"
  val CD = "cd"

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
      if (tokens.length < 2) incompleteCommand(MKDIR)
      else new Mkdir(tokens(1))
    else if (LS.equals(tokens(0)))
      new Ls
    else if (PWD.equals(tokens(0)))
      new Pwd
    else if (TOUCH.equals(tokens(0)))
      if (tokens.length < 2) incompleteCommand(TOUCH)
      else new Touch(tokens(1))
    else if (CD.equals(tokens(1)))
      if (tokens.length < 2) incompleteCommand(TOUCH)
      else new Cd(tokens(1))
    else new UnknownCommand
  }

}