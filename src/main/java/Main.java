import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.web.config.ApplicationContextConfig;

public class Main {
public static void main(String[] args) throws SQLException
{
	ApplicationContext ctx = 
			   new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
			   
	DataSource helloWorld = (DataSource)ctx.getBean("dataSource");

			   Connection connection = helloWorld.getConnection();
			   
			   if(connection == null)
			   {
				   System.out.println("dfgfdg");
			   } else {
				   System.out.println("cool man");
			   }
			   
			   
}
}
