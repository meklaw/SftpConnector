//package ru.meklaw
//
//import com.jcraft.jsch.ChannelSftp
//
//import java.io.OutputStream
//
//class SftpOutputStream(sftp: ChannelSftp, filePath: String, mode: Int = ChannelSftp.OVERWRITE) extends OutputStream {
//  private val outputStream = sftp.put(filePath, mode)
//
//  override def write(b: Int): Unit = outputStream.write(b)
//
//  override def write(b: Array[Byte]): Unit = outputStream.write(b)
//
//  override def write(b: Array[Byte], off: Int, len: Int): Unit = outputStream.write(b, off, len)
//
//  override def flush(): Unit = outputStream.flush()
//
//  override def close(): Unit = outputStream.close()
//}
//
