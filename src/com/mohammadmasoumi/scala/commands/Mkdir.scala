package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.{DirEntry, Directory}
import com.mohammadmasoumi.scala.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {

  override def CreateSpecificEntry(state: State, entryName: String): DirEntry =
    Directory.empty(state.wd.path, entryName)

}
