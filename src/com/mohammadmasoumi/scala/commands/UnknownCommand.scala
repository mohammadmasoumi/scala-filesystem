package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.filesystem.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage("Command not found")
}
