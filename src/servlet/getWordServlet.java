package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import cloud_final.ConnectToFireBase;
import cloud_final.Word;

/**
 * Servlet implementation class getWordServlet
 */
@WebServlet("/getWordServlet")
public class getWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectToFireBase connectToFireBase = new ConnectToFireBase();
		System.out.println("Helloooooooooooo");
		Word word = new Word();
		word.setName("hello");
		word.setDefinition("used as a greeting or to begin a telephone conversation.");
		boolean exception = true;
		System.out.println("Helloooooooooooo");
		while(exception){
			try{
			connectToFireBase.run();
			word = connectToFireBase.getWord();
			System.out.println(word.getName()+ "  HERE   " + word.getDefinition());
			String def = word.getDefinition();
			exception = false;
			}catch(Exception e){
				
			}
		}
			
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("wordName", word.getName());
		obj.put("definition", word.getDefinition());
		out.print(obj);
		
		
	}
	
}
