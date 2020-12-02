package com.mohammadmasoumi.scala.files

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def hasEntry(name: String): Boolean = ???

  def getAllFoldersInPath: List[String] = ???

  def findDescendant(path: List[String]): Directory = ???
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  // instantiating objects with methods
  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())
}