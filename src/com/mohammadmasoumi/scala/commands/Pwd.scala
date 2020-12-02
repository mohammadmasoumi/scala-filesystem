package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.filesystem.State

class Pwd extends Command {
  override def apply(state: State): State =
    state.setMessage(state.wd.path)
}