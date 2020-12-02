package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.{DirEntry, Directory}
import com.mohammadmasoumi.scala.filesystem.State

abstract class CreateEntry(entryName: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd

    if (wd.hasEntry(entryName))
      state.setMessage("Entry " + entryName + " already exists!")
    else if (entryName.contains(Directory.SEPARATOR))
      state.setMessage("Must no contain separators!")
    else if (checkIllegal(entryName))
      state.setMessage(entryName + ": illegal entry name!")
    else doMkdir(state, entryName)
  }

  private def checkIllegal(name: String): Boolean =
    name.contains(".")

  private def doMkdir(state: State, name: String): State = {
    // try to name variables as general as possible
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        // currentDirectory
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    // 1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    val newEntry: DirEntry = createSpecificEntry(state)

    // 3. update the whole directory structure starting from the root
    // the directory structure is IMMUTABLE
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    // 4. find new working directory INSTANCE given wd's full path, in the NEW directory structure.
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State): DirEntry

}
