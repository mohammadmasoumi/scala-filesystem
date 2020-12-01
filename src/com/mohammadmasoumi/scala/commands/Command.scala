package com.mohammadmasoumi.scala.commands

import com.mohammadmasoumi.scala.filesystem.State

/*
  Transform an state to another state
 */
trait Command {

  def apply(state: State): State

}
