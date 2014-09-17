package edu.gatech.omscs.cs6310.Interfaces;

public class MemInfo {
	private static final long MEGABYTE = 1024L * 1024L;
	
	public static long bytesToMegaBytes(long bytes) {
		return bytes / MEGABYTE;
	}
	
	public static long getCurrentMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		
		runtime.gc();
		
		long mem = runtime.totalMemory() - runtime.freeMemory();
		
		return MemInfo.bytesToMegaBytes(mem);
	}
}
