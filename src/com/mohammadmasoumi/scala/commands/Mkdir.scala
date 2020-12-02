package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.Directory
import com.mohammadmasoumi.scala.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean =
    name.contains(".")

  def doMkdir(state: State, name: String): State = state

  override def apply(state: State): State = {
    val wd = state.wd

    if (wd.hasEntry(name))
      state.setMessage("Entry " + name + " already exists!")
    else if (name.contains(Directory.SEPARATOR))
      state.setMessage("Must no contain separators!")
    else if (checkIllegal(name))
      state.setMessage(name + ": illegal entry name!")
    else doMkdir(state, name)
  }

}
