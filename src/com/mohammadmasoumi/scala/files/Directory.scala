package com.mohammadmasoumi.scala.files

import com.mohammadmasoumi.scala.filesystem.FilesystemException

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def addEntry(newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents :+ newEntry)

  def removeEntry(head: String) = ???

  def findEntry(entryName: String): DirEntry = {
    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)
    }

    findEntryHelper(entryName, contents)
  }

  def hasEntry(name: String): Boolean =
    findEntry(name) != null

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)

  def isRoot: Boolean = parentPath.isEmpty

  def findDescendant(path: List[String]): Directory =
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)

  def findDescendant(relativePath: String): Directory = ???

  def getAllFoldersInPath: List[String] =
  // /a/b/c/d => List["a","b","c","d"]
    path.substring(1).split(Directory.SEPARATOR).toList.filter(!_.isEmpty)

  override def asDirectory: Directory = this

  override def getType: String = "Directory"

  override def asFile: File = throw new FilesystemException("A directory cannot be converted to a file!")

  override def isDirectory: Boolean = true

  override def isFile: Boolean = false
}


object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"
  val INVALID_RELATIVE_PATH = "."
  val VALID_RELATIVE_PATH = ".."

  def ROOT: Directory = Directory.empty("", "")

  // instantiating objects with methods
  def empty(parentPath: String, name: String) =
    new Directory(parentPath, name, List())
}