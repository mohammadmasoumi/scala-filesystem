package com.mohammadmasoumi.scala.filesystem

import java.util.Scanner

import com.mohammadmasoumi.scala.commands.Command
import com.mohammadmasoumi.scala.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input)(state) // state full program

  }
}
