package com.mohammadmasoumi.scala.files

abstract class DirEntry(val parentPath: String, val name: String) {

  def path: String = parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory

  def asFile: File

  def getType: String
}
