package idv.service;

import java.io.File;
import java.io.IOException;

public class DiskInfoService {
	private long usableSpace;
	private long totalSpace;
	public void setDiskInfo(String path)throws IOException  {
		File f = new File(path);
		this.usableSpace = f.getUsableSpace()/1024/1024/1024;
		this.totalSpace = f.getTotalSpace()/1024/1024/1024;
	}
	
	public long getUsableSpace() {
		return this.usableSpace;
	}
	
	public long getTotalSpace() {
		return this.totalSpace;
	}
}
