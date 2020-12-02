package com.mohammadmasoumi.scala.files

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def replaceEntry(entryName: String, newEntry: DirEntry): Directory = ???

  def findEntry(entryName: String): DirEntry = {
    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)
    }

    findEntryHelper(entryName, contents)
  }

  def hasEntry(name: String): Boolean = ???

  def getAllFoldersInPath: List[String] =
  // /a/b/c/d => List["a","b","c","d"]
    path.substring(1).split(Directory.SEPARATOR).toList

  def findDescendant(path: List[String]): Directory = ???

  def addEntry(newEntry: DirEntry): Directory = ???

  override def asDirectory: Directory = this
}


object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  // instantiating objects with methods
  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())
}