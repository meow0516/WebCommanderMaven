package idv.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import idv.model.FileInfo;

public class FileInfoService {
	private String currentPath;
	private List<FileInfo> fileList;
	private List<FileInfo> folderList;

	public void setFileInfos(String path) throws IOException {
		this.currentPath = path;
		this.fileList = new ArrayList<FileInfo>();
		this.folderList = new ArrayList<FileInfo>();

//		get full list in the folder
		File f = null;
		String[] list;
		f = new File(path);
		list = f.list();

		for (String item : list) {
			String fullPathName = path + item;
			File file = new File(fullPathName);
			Path fullPath = Paths.get(fullPathName);
			BasicFileAttributes attr;
			attr = Files.readAttributes(fullPath, BasicFileAttributes.class);

			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(item);
			fileInfo.setSize(attr.size());
			fileInfo.setLastModified(attr.lastModifiedTime());

			if (file.isDirectory()) {
				fileInfo.setIsDirectory(true);
				fileInfo.setExtension(null);
				this.folderList.add(fileInfo);
			}
//			.extension
			else {
				fileInfo.setIsDirectory(false);
				this.fileList.add(fileInfo);
			}
		}
	}

	public String getCurrentPath() {
		return this.currentPath;
	}

	public List<FileInfo> getFileList() {
		return this.fileList;
	}

	public List<FileInfo> getFolderList() {
		return this.folderList;
	}

	public double getTotalSize() {
		double sum = 0;
		for (FileInfo file : this.fileList) {
			sum += file.getSize();
		}
		return sum;
	}

	public int getFileCount() {
		int sum = 0;
		for (FileInfo file : this.fileList) {
			sum += 1;
		}
		return sum;
	}

	public int getFolderCount() {
		int sum = 0;
		for (FileInfo folder : this.folderList) {
			sum += 1;
		}
		return sum;
	}
}
