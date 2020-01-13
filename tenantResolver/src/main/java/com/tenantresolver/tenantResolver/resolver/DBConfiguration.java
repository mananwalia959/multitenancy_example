package com.tenantresolver.tenantResolver.resolver;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DBConfiguration {
		@Id
		Integer tenantId;
		
		
		String host;
		String userName;
		String password;
		String dbName;
		boolean ssl;
		int port = 1433; //default value should be 1433
		public Integer getTenantId() {
			return tenantId;
		}
		public void setTenantId(Integer tenantId) {
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
		public void setUserName(String userName) {
			this.userName = userName;
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
		public void setDbName(String dbName) {
			this.dbName = dbName;
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
