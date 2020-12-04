package com.mohammadmasoumi.scala.commands
import com.mohammadmasoumi.scala.files.Directory
import com.mohammadmasoumi.scala.filesystem.State

class Rm(name: String) extends Command{


  override def apply(state: State): State = {

    // 1. get current working dir
    val wd = state.wd

    // 2. get absolute path
    val absolutePath =
      if (name.startsWith(Directory.SEPARATOR)) name
      else if (wd.isRoot) wd.path + name
      else wd.path + Directory.SEPARATOR + name

    // 3. do some checks
    if (Directory.ROOT_PATH.equals(absolutePath))
      state.setMessage("Not supported yet!")
    else
      doRm(state, absolutePath)

  }

  def doRm(state: State, path: String): State = {
    // 4. find the entry to remove

    // 5. update structure like we did for mkdir
  }

}
