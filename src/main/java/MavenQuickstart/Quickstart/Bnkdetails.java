/**
 * 
 */
package MavenQuickstart.Quickstart;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.Engine.Model.StatementVO;
import com.Engine.Model.User;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * @author ManiShankarGajavelli
 *
 */
public class Bnkdetails {
	
	static Logger LOGGER=Logger.getLogger(Bnkdetails.class);
	
	
	static PdfReader reader;
	static User currentuser;
	static ArrayList<StatementVO> Stmtsvo;
	static SimpleDateFormat dateformatter=new SimpleDateFormat("dd/MM/yyyy");  
	static SimpleDateFormat dateformatter1=new SimpleDateFormat("MMMMM d, yyyy");  
	static SimpleDateFormat dateformatter2=new SimpleDateFormat("E");
	/**
	 */
	public static void getextractReport() {
	LOGGER.debug("entered into getextractreport");
	      try {
	          reader = new PdfReader("pdffile.pdf");
	           currentuser=new User();
	           Stmtsvo=new ArrayList<StatementVO>();
	          int count = reader.getNumberOfPages();
	          String textFromPage ="";
	          textFromPage = PdfTextExtractor.getTextFromPage(reader,1);
	           int headerend=textFromPage.indexOf("Transaction Value Date");
	           if (headerend== -1) {
				return;
			}
	           String HeaderContent=textFromPage.substring(0, headerend);
	           getUserDetails(HeaderContent);
	           textFromPage="";
	          for (int i = 1; i <= count; i++) {
	              textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
//	              System.out.println(textFromPage);
	              StatementVO Stmt = null;
	              if (i==1) {
					textFromPage=textFromPage.substring(headerend+2);
				}
	              String[] Pagecontents=textFromPage.split("\n");
	              for (int j = 0; j < Pagecontents.length; j++) {
	            	  String[] Statement =Pagecontents[j].split(" ");
	            	  if(Statement[0].matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
	            		  		Stmt=new StatementVO();
								Stmt.setTransactionDate(dateformatter.parse(Statement[0]));
								Stmt.setValueDate(dateformatter.parse(Statement[1]));
								Stmt.setDebit(Statement[Statement.length-3]);
								Stmt.setCredit(Statement[Statement.length-2]);
								Stmt.setBalance(Statement[Statement.length-1]);
								Stmt.setDescription(Statement[2]);
								for (int k = 3; k < Statement.length; k++) {
									if(!Statement[k].contains(".")){
										Stmt.setDescription(Stmt.getDescription() + Statement[k]);
									}
								}
								if((!Pagecontents[j+1].split(" ")[0].matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))){
									String[] Statement1 =Pagecontents[j].split(" ");
										if(Statement1[0].matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
									Stmt.setDescription(Stmt.getDescription() +Pagecontents[j+1] );
								}
	            	  }
	            	  }
	            	  /*if(StmtGrid==true && !Statement[0].matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")){
	            		  Stmt.setDescription(Stmt.getDescription() + Statement[j]);
	            	  }*/
	            	  if(Stmt!=null){
	            		  Stmtsvo.add(Stmt);
	            		  Stmt=null;
	            	  }
				}
	          }
	          reader.close();
	          double totalDebth=0.0;
	          double totalCredit=0.0;
	          for (Iterator<StatementVO> iterator = Stmtsvo.iterator(); iterator
						.hasNext();) {
					StatementVO statementVO = (StatementVO) iterator.next();
					/*System.out.print(statementVO.getTransactionDate() + " ");
					System.out.print(statementVO.getValueDate()+" ");
					System.out.print(statementVO.getDescription() + " ");
					System.out.print(statementVO.getCredit()+ " ");*/
//					System.out.print(statementVO.getDebit()+" ");
//					System.out.println(statementVO.getBalance()+" ");
	
//					System.out.println(statementVO.getCredit());
					
					totalDebth=totalDebth+Double.parseDouble((statementVO.getDebit().replace(",", "")));
					totalCredit=totalCredit+Double.parseDouble((statementVO.getCredit().replace(",", "")));
				} 
//	          System.out.println(totalCredit);
//	          System.out.println(totalDebth);
//	          System.out.println(totalCredit-totalDebth);
	          
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	     catch (ParseException e) {
				e.printStackTrace();
			}
	}

	/**
	 * @param textFromPage
	 */
	public static void getUserDetails(String textFromPage) {
		String[] Pagecontents=textFromPage.split("\n");
		try {
		for (int j = 0; j < Pagecontents.length; j++) {
		  if (Pagecontents[j].contains("Period :")) {
			String date = Pagecontents[j].substring(Pagecontents[j].indexOf(":")+1);
			String[] date1=date.split("to");
				currentuser.setFromAccDate(dateformatter1.parse(date1[0].trim()));
				currentuser.setToAccDate(dateformatter1.parse(date1[1].trim()));
			}
		  if (Pagecontents[j].contains("Your Branch Details")) {
				currentuser.setL_sName(Pagecontents[j+1].trim());
				String address="";
				int i=j+2;
				while (!(Pagecontents[i].contains("@")&& Pagecontents[i].contains("*")&&Pagecontents[i].contains("."))) {
					address+=Pagecontents[i]+" ";
					i=i+2;
				}
				currentuser.setL_sAddress(address);
		  }
		  if (Pagecontents[j].contains("Name : ")) {
			  currentuser.setL_sBnkBranchName(Pagecontents[j].substring(Pagecontents[j].indexOf(":")+1).trim());
		  }
		  if (Pagecontents[j].contains("Address :")) {
			  String BnkAddress=Pagecontents[j].substring(Pagecontents[j].indexOf(":")+1)+" ";
			  int i=j+2;
				while ((!(Pagecontents[i].contains("@")&& Pagecontents[i].contains("*")&&Pagecontents[i].contains(".")))&&(!Pagecontents[i].contains("IFSC"))) {
					BnkAddress+=Pagecontents[i]+" ";
					i=i+2;
				}
				currentuser.setL_sBnkAddress(BnkAddress);
		  }
		  if ((Pagecontents[j].contains("@")&& Pagecontents[j].contains("*")&&Pagecontents[j].contains("."))) {
		  currentuser.setL_sEmail(Pagecontents[j]);
		  }
		  if (Pagecontents[j].startsWith("IFSC")) {
				currentuser.setL_sIFCSCode(Pagecontents[j].substring(Pagecontents[j].indexOf(": ")+1,Pagecontents[j].indexOf("M")).trim());
				currentuser.setL_sMICR(Pagecontents[j].substring(Pagecontents[j].lastIndexOf(":")+1).trim());			
		}
		  if (Pagecontents[j].startsWith("Cust Id")) {
			  currentuser.setL_sCustomerId(Pagecontents[j]);
		  }
		  if (Pagecontents[j].contains("ACCOUNT No.")) {
			  int accindex=Pagecontents[j].indexOf(".");
			  currentuser.setL_sAccountNo(Pagecontents[j].substring(accindex+1,accindex+16).trim());
			  int currindex=Pagecontents[j].indexOf(":");
			  currentuser.setL_sCurrencyType(Pagecontents[j].substring(currindex+1,Pagecontents[j].length()-1).trim());
		  }
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static HashMap<Integer, StatementVO> getDetailsByDay(){
		HashMap<Integer, StatementVO> DayReport=new HashMap<Integer, StatementVO> ();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < Stmtsvo.size(); i++) {
			cal.setTime(Stmtsvo.get(i).getValueDate());
			if (Calendar.SUNDAY==cal.get(Calendar.DAY_OF_WEEK)) {
				DayReport.put(Calendar.SUNDAY, Stmtsvo.get(i));
			}
			if (Calendar.MONDAY==cal.get(Calendar.DAY_OF_WEEK)) {
				DayReport.put(Calendar.MONDAY, Stmtsvo.get(i));
			}
			if (Calendar.TUESDAY==cal.get(Calendar.DAY_OF_WEEK)) {
				DayReport.put(Calendar.TUESDAY, Stmtsvo.get(i));
			}
			if (Calendar.THURSDAY==cal.get(Calendar.DAY_OF_WEEK)	) {
				DayReport.put(Calendar.THURSDAY, Stmtsvo.get(i));
			}
			if (Calendar.FRIDAY==cal.get(Calendar.DAY_OF_WEEK)	) {
				DayReport.put(Calendar.FRIDAY, Stmtsvo.get(i));
			}
			if (Calendar.SATURDAY==cal.get(Calendar.DAY_OF_WEEK)	) {
				DayReport.put(Calendar.SUNDAY, Stmtsvo.get(i));
			}
			
			
		}
		return DayReport;
	}

	/**
	 * 
	 */
	public Bnkdetails() {
		super();
	}

}