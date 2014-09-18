package edu.gatech.omscs.cs6310.Interfaces;

public class MemInfo {
	private static final long MEGABYTE = 1024L * 1024L;
	
	/**
	 * Convert bytes to Megabytes
	 * @param bytes - Number of bytes
	 * @return Converted number of Megabytes
	 */
	private static long bytesToMegaBytes(long bytes) {
		return bytes / MEGABYTE;
	}
	
	/**
	 * Gets current memory usage
	 * @return Memory usage in Megabytes
	 */
	public static long getCurrentMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		
		runtime.gc();
		
		long mem = runtime.totalMemory() - runtime.freeMemory();
		
		return MemInfo.bytesToMegaBytes(mem);
	}
}