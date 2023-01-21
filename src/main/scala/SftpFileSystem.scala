//package ru.meklaw
//
//import com.jcraft.jsch.{ChannelSftp, JSch, Session}
//import org.apache.hadoop.fs._
//import org.apache.hadoop.fs.permission.FsPermission
//import org.apache.hadoop.util.Progressable
//
//import java.io.BufferedOutputStream
//import java.net.URI;
//
//class SftpFileSystem extends FileSystem {
//  private val jsch = new JSch()
//  private var session: Session = _
//  private var sftp: ChannelSftp = _
//
//  override def getUri: URI = {
//    // return the URI of the SFTP server
//    new URI("sftp", sftp.getHome(), null, null)
//  }
//
//  override def open(path: Path, i: Int): FSDataInputStream = {
//    val fileSize = sftp
//      .lstat(path
//        .toString)
//      .getSize
//    new FSDataInputStream(new BufferedFSInputStream(new SftpInputStream(sftp, path
//      .toString), fileSize
//      .toInt))
//  }
//
//
//  override def create(path: Path, fsPermission: FsPermission, b: Boolean, i: Int, i1: Short, l: Long, progressable: Progressable): FSDataOutputStream = {
//    val sftpOutputStream = new SftpOutputStream(sftp, path
//      .toString)
//    val startPosition = 0
//    val statistics = FileSystem.getStatistics("sftp", this
//      .getClass)
//    new FSDataOutputStream(sftpOutputStream, statistics, startPosition)
//  }
//
//
//  override def append(path: Path, i: Int, progressable: Progressable): FSDataOutputStream = {
//    val sftpOutputStream = new SftpOutputStream(sftp, path
//      .toString, ChannelSftp
//      .APPEND)
//    val statistics = FileSystem.getStatistics("sftp", this
//      .getClass)
//    new FSDataOutputStream(sftpOutputStream, statistics)
//  }
//
//
//  override def rename(path: Path, path1: Path): Boolean = {
//    // rename a remote file
//    sftp.rename(path
//      .toString, path1
//      .toString)
//    true
//  }
//
//  override def delete(path: Path, b: Boolean): Boolean = {
//    // delete a remote file
//    sftp.rm(path
//      .toString)
//    true
//  }
//
//  override def listStatus(path: Path): Array[FileStatus] = {
//    val files = sftp
//      .ls(path
//        .toString)
//      .asInstanceOf[java.util.Vector[LsEntry]]
//    files
//      .asScala
//      .map(file => {
//        new FileStatus(file
//          .getAttrs
//          .getSize, file
//          .getAttrs
//          .isDir, 1, 4096, file
//          .getAttrs
//          .getMTime * 1000, 0, FsPermission
//          .getFileDefault, "", "", new Path(file
//          .getFilename))
//      })
//      .toArray
//  }
//
//
//  override def setWorkingDirectory(path: Path): Unit = {
//    // set the working directory on the remote SFTP server
//    sftp.cd(path
//      .toString)
//  }
//
//  override def getWorkingDirectory: Path = {
//    // get the current working directory on the remote SFTP server
//    new Path(sftp
//      .pwd)
//  }
//
//  override def mkdirs(path: Path, fsPermission: FsPermission): Boolean = {
//    // create a remote directory
//    sftp.mkdir(path
//      .toString)
//    true
//  }
//
//  override def getFileStatus(path: Path): FileStatus = {
//    // get the status of a remote file
//    val attrs = sftp.stat(path
//      .toString)
//    new FileStatus(attrs
//      .getSize, attrs
//      .isDir, 1, 4096, attrs
//      .getMTime * 1000, 0, FsPermission
//      .getFileDefault, "", "", path)
//  }
//
//  override def connect(uri: URI, configuration: Configuration): Unit = {
//    // establish a connection to the SFTP server
//    val user = configuration.get("fs.sftp.username")
//    val host = uri
//      .getHost
//    val port = uri
//      .getPort
//    val password = configuration.get("fs.sftp.password")
//    session = jsch.getSession(user, host, port)
//    session.setConfig("StrictHostKeyChecking", "no")
//    session.setPassword(password)
//    session.connect()
//    sftp = session
//      .openChannel("sftp")
//      .asInstanceOf[ChannelSftp]
//    sftp.connect()
//  }
//
//  override def close(): Unit = {
//    // close the SFTP connection
//    sftp.disconnect()
//    session.disconnect()
//  }
//}
