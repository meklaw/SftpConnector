//package ru.meklaw
//
//import org.apache.hadoop.fs.FSInputStream
//
//class SftpInputStream(sftp: ChannelSftp, filePath: String) extends FSInputStream {
//  private val inputStream = sftp.get(filePath)
//  private var pos: Long = 0
//
//  override def read(): Int = {
//    val value = inputStream.read()
//    if (value != -1) {
//      pos += 1
//    }
//    value
//  }
//
//  override def read(b: Array[Byte]): Int = {
//    val value = inputStream.read(b)
//    if (value != -1) {
//      pos += value
//    }
//    value
//  }
//
//  override def read(b: Array[Byte], off: Int, len: Int): Int = {
//    val value = inputStream.read(b, off, len)
//    if (value != -1) {
//      pos += value
//    }
//    value
//  }
//
//  override def available(): Int = inputStream.available()
//
//  override def close(): Unit = inputStream.close()
//
//  override def seek(pos: Long): Unit = {
//    inputStream.skip(pos - this.pos)
//    this.pos = pos
//  }
//
//  override def getPos: Long = pos
//
//  override def seekToNewSource(targetPos: Long): Boolean = false
//}
//
//
