package interfaces;

public class MemInfo {
	private static final long KILOBYTE = 1024L;
	
	/**
	 * Convert bytes to Megabytes
	 * @param bytes - Number of bytes
	 * @return Converted number of Megabytes
	 */
	private static long bytesToMegaBytes(long bytes) {
		return bytes / KILOBYTE;
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