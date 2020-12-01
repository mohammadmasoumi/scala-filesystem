package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.Directory
import com.mohammadmasoumi.scala.filesystem.State

class Mkdir(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd

    if (wd.hasEntry(name)) state.setMessage("Entry " + name + " already exists!")
    else if (name.contains(Directory.SEPARATOR)) state.setMessage("Must no contain separators!")
  }

}
