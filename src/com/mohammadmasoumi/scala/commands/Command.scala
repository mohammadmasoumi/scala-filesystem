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
  val RM = "rm"
  val ECHO = "echo"
  val CAT = "cat"

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
    else tokens(0) match {
      case LS => new Ls
      case PWD => new Pwd
      case MKDIR =>
        if (tokens.length < 2) incompleteCommand(MKDIR)
        else new Mkdir(tokens(1))
      case TOUCH =>
        if (tokens.length < 2) incompleteCommand(TOUCH)
        else new Touch(tokens(1))
      case CD =>
        if (tokens.length < 2) incompleteCommand(CD)
        else new Cd(tokens(1))
      case RM =>
        if (tokens.length < 2) incompleteCommand(RM)
        else new Rm(tokens(1))
      case CAT =>
        if (tokens.length < 2) incompleteCommand(CAT)
        else new Cat(tokens(0))
      case ECHO =>
        if (tokens.length < 2) incompleteCommand(ECHO)
        else new Echo(tokens.tail)
      case _ => new UnknownCommand
    }
  }
}