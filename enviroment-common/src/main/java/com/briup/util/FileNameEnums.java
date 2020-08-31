package com.briup.util;

public enum FileNameEnums {

	CLIENT_RADWTMP_PATH("client_radwtmp_path", "src/main/resources/radwtmp"),
	CLIENT_RADWTMP2_PATH("client_radwtmp2_path", "src/main/resources/radwtmp2"),
	CLIENT_NUM_PATH("client_num_path", "src/main/resources/num.txt"),
	CLIENT_DATA_PATH("client_data_path", "src/main/resources/data.txt"),
	CLIENT_EXDATA_PATH("client_exdata_path", "src/main/resources/client-data.txt");

	private String fileName;
	private String path;

	private FileNameEnums(String fileName, String path) {
		this.fileName = fileName;
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
