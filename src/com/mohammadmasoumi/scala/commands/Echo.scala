package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.filesystem.State

class Echo(args: Array[String]) extends Command {
  override def apply(state: State): State = {
    /*
      if no args, state
      else if just one arg, print to console
      else if multiple args
      {
        operator = next to last argument

        if >
          echo to a file (may create a file if not there)
        if >>
          append to a file
        else
          just echo everything to the console
      }
     */

    if (args.isEmpty) state
    else if (args.length == 1) state.setMessage(args(0))
    else {
      val operator = args(args.length - 2)
      val filename = args(args.length - 1)
      val contents = createContent(args, args.length - 2)


    }
  }


  // topIndex - NON-INCLUSIVE
  def createContent(args: Array[String], topIndex: Int): String = {

  }


}
