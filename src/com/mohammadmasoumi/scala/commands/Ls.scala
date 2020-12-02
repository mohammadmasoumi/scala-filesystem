package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.files.DirEntry
import com.mohammadmasoumi.scala.filesystem.State

class Ls extends Command {

  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createNiceOutput(contents)
    state.setMessage(niceOutput)
  }

  private def createNiceOutput(contents: List[DirEntry]): String =
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name + s"[${entry.getType}]" + "\n" + createNiceOutput(contents.tail)
    }

}
