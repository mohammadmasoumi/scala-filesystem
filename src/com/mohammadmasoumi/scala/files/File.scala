package com.mohammadmasoumi.scala.files

import com.mohammadmasoumi.scala.filesystem.FilesystemException


class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory =
    throw new FilesystemException("A file can't be converted to a directory!")

  override def getType: String = this.getClass.toString

  override def asFile: File = this
}
