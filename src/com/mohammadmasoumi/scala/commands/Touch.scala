package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.{DirEntry, File}
import com.mohammadmasoumi.scala.filesystem.State

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
