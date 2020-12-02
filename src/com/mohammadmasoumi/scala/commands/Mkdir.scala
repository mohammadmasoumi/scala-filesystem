package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.Directory
import com.mohammadmasoumi.scala.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean =
    name.contains(".")

  def doMkdir(state: State, name: String): State = {
    // try to name variables as general as possible
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: Directory): Directory = ???

    val wd = state.wd

    // 1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    val newDir = Directory.empty(wd.path, name)

    // 3. update the whole directory structure starting from the root
    // the directory structure is IMMUTABLE
    val newRoot = updateStructure(state.root, allDirsInPath, newDir)

    // 4. find new working directory INSTANCE given wd's full path, in the NEW directory structure.
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

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
