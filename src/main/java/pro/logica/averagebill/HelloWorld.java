package pro.logica.averagebill;

import java.util.Date;
import javax.inject.Named;

@Named
public class HelloWorld {
	public String getTime() {
		return new Date().toString();
	}
}