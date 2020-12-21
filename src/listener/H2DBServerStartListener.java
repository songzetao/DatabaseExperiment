package listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.tools.Server;

@WebListener
public class H2DBServerStartListener implements ServletContextListener {

	private Server server;

	public H2DBServerStartListener() {
	}

	public void contextDestroyed(ServletContextEvent sce) {
		if (this.server != null) {
			this.server.stop();
			System.err.println("H2 Database stoped.");
			this.server = null;
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			System.err.println("H2 Database starting ...");
			server = Server.createTcpServer().start();
			System.err.println("H2 Database running ...");
		} catch (SQLException e) {
			System.err.println("H2 Database start failed ...");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
