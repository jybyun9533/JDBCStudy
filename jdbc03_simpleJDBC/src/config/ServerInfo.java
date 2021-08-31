package config;

public interface ServerInfo {
	// public static final 생략
	String DRIVER = "com.mysql.cj.jdbc.Driver";
	String URL = "jdbc:mysql://127.0.0.1:3306/scott?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	String USER = "root";
	String PASSWORD = "1234";

}
