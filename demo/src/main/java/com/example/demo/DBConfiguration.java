package com.example.demo;

public class DBConfiguration {
	
		int tenantId;

		String host;
		String userName;
		String password;
		String dbName;
		
		boolean ssl;
		int port = 1433; //default value should be 1433
		
		
		
		public int getTenantId() {
			return tenantId;
		}
		public void setTenantId(int tenantId) {
			this.tenantId = tenantId;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String user) {
			this.userName = user;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getDbName() {
			return dbName;
		}
		public void setDbName(String database) {
			this.dbName = database;
		}
		public boolean isSsl() {
			return ssl;
		}
		public void setSsl(boolean ssl) {
			this.ssl = ssl;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
	

}
