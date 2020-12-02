package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.filesystem.State

class Cd(dir: String) extends Command {

  override def apply(state: State): State = {

    // 1. find root
    val root = state.root
    val wd = state.wd

    // 2. find the absolute path of the directory I want to cd to


    // 3. find the directory to cd to, given the path

    // 4. change the state - given the new directory


  }

}
