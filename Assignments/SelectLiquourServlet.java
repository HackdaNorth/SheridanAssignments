package sheridan;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name = "selectliquourservlet",
        urlPatterns = "/SelectLiquor"
)

public class SelectLiquourServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1831978487671842618L;
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String liquorType = req.getParameter("Type");

        LiquourService liquorService = new LiquourService();
        LiquourType l = LiquourType.valueOf(liquorType);

        List<String> liquorBrands = liquorService.getAvailableBrands(l);

        req.setAttribute("brands", liquorBrands);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }	

}
