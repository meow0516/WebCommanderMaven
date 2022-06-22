package idv.model;

import java.nio.file.attribute.FileTime;

public class FileInfo {
	private String fileName;
	private FileTime lastModified;
	private boolean isDirectory;
	private String extension;
	private long size;
	private String historyPath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(FileTime lastModified) {
		this.lastModified = lastModified;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setIsDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long l) {
		this.size = l;
	}

	public String getHistoryPath() {
		return historyPath;
	}

	public void setHistoryPath(String historyPath) {
		this.historyPath = historyPath;
	}
}
