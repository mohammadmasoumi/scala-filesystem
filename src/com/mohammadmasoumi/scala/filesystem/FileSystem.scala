package com.mohammadmasoumi.scala.filesystem

import com.mohammadmasoumi.scala.commands.Command
import com.mohammadmasoumi.scala.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT
  io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
    currentState.show
    Command.from(newLine)(currentState)
  })

  //  var state = State(root, root)
  //  val scanner = new Scanner(System.in)
  //
  //  while (true) {
  //    state.show
  //    val input = scanner.nextLine()
  //    state = Command.from(input)(state) // state full program
  //  }
}
