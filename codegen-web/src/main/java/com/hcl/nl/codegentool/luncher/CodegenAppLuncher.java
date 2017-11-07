package com.hcl.nl.codegentool.luncher;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import com.hcl.nl.codegentool.logging.CodegenAppLogger;

/**
 * 
 * @author masud
 *
 */
public class CodegenAppLuncher {

	public static final String DEFAULT_PORT = "8086";
	public static final String CONTEXT_ROOT = "codegen";

	private static final CodegenAppLogger LOGGER = CodegenAppLogger.logger(CodegenAppLuncher.class);

	/**
	 * 
	 * @param args
	 * @throws ServletException
	 * @throws LifecycleException
	 */
	public static void main(String... args) throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		// The port that we should run on can be set into an environment variable
		// Look for that variable and default to 8086 if it isn't there.
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = DEFAULT_PORT;
		}
		tomcat.setPort(Integer.valueOf(webPort));
		// This is to compensate for the different paths that the appassembler-program
		// and jsw daemon use
		String basedir = System.getProperty("basedir");
		String deamonWebapp = System.getProperty("deamon-webapp");
		if (null != basedir) {
			basedir += "/webapp";
		} else if (null != deamonWebapp) {
			basedir = deamonWebapp;
		} else {
			basedir = "src/main/webapp";
		}
		LOGGER.appendInfo("Basedir: " + basedir);
		String webappLocation = new File(basedir).getAbsolutePath();
		LOGGER.appendInfo("configuring app with basedir: " + webappLocation);
		tomcat.addWebapp("/" + CONTEXT_ROOT, webappLocation);
		tomcat.start();
		tomcat.getServer().await();
	}

}
