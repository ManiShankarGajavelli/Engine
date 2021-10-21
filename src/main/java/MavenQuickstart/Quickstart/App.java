package MavenQuickstart.Quickstart;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.Engine.Model.StatementVO;
import com.Engine.Model.User;

public class App
{
	static Logger LOGGER=Logger.getLogger(App.class);
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.debug("This is my first log4j's statement");
		SimpleDateFormat dateformatter=new SimpleDateFormat("dd/MM/yyyy"); 
		Date str_date = null;
		try {
			str_date = dateformatter.parse("5/12/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bnkdetails.getextractReport();
		HashMap<Integer, StatementVO> DayReport=Bnkdetails.getDetailsByDay();
		User currentuser=Bnkdetails.currentuser;
		
		ArrayList<StatementVO> Stmtsvo=Bnkdetails.Stmtsvo;
		StatementVO stmt=null;
//		long endTime =currentuser.getToAccDate().getTime() ;
		long curTime = str_date.getTime();
		for (int i = 0; i < Stmtsvo.size(); i++) {
			while (curTime >= Stmtsvo.get(i).getTransactionDate().getTime()) {
				System.out.println(Stmtsvo.get(i).getDescription());
				System.out.println(Stmtsvo.get(i).getBalance());
				break;
			}
		}
		
		
		
		
		
	}
}