package ua.nure.jurkov.SummaryTask4.controller.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;

/**
 * Servlet Filter implementation class SecurityFilter
 */
@WebFilter(value = "/controller/*", initParams = {
@WebInitParam(name = "commonActions", value = "/CoursesView /LogIn /Register " +
		"/CoursesSelectByLecturer /CoursesSelectByTopic /CoursesSort /en /ru "),
@WebInitParam(name = "userActions", value = "/LogOut /Profile "),
@WebInitParam(name = "studentActions", value = "/ViewSelectedStudentAction /EnrollAtTheCourse "),
@WebInitParam(name = "lecturerActions", value = "/ViewSelectedLecturerAction /Journal /Estimate "),
@WebInitParam(name = "adminActions", value = "/AdminViewLecturers /ViewSelectedCourses " + 
		"/ViewSelectedLecturers /SetLecturerOnCourseView /CoursesOfLecturer /SetLecturerOnCourse " +
		"/ViewStudents /AddBlockStudent /DeletBlockStudent /ViewSelectedAdminAction /ViewEditCourses " +
		"/ViewNewCourse /createCourse /CourseEdit /RegisterLecturer /CourseDelete ")
})
public class SecurityFilter implements Filter {
	private static final Logger LOG = Logger.getLogger(SecurityFilter.class);
	
	private static Map<String, List<String>> accessMap = new HashMap<>();
	private static List<String> commonActions = new ArrayList<>();
	private static List<String> userActions = new ArrayList<>();

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.debug("Filter destruction start");
		
		LOG.debug("Filter destruction finished");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.debug("Filter starts");
		
		if(accessAllowed(request)){
			LOG.debug("Filter finished");
			chain.doFilter(request, response);
		}
		else{
			request.setAttribute("errorInput", "Without access rights!");
			LOG.trace("Set the request attribute: errorMessage --> Without access reights");
			
			request.getRequestDispatcher("/WEB-INF/view/CoursesView.jsp")
				.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter init starts");
		
		accessMap.put("Admin", toList(fConfig.getInitParameter("adminActions")));
		accessMap.put("Student", toList(fConfig.getInitParameter("studentActions")));
		accessMap.put("Lecturer", toList(fConfig.getInitParameter("lecturerActions")));
		LOG.trace("Access map --> " + accessMap);
		
		commonActions = toList(fConfig.getInitParameter("commonActions"));
		LOG.trace("Common actions --> " + commonActions);
		
		userActions = toList(fConfig.getInitParameter("userActions"));
		LOG.trace("User action --> " + userActions);
		
		LOG.debug("Filter init finished");
	}
	
	private boolean accessAllowed(ServletRequest request){
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		String action = httpRequest.getPathInfo();
		LOG.trace("Action name: " + action);
		
		if(action == null || action.isEmpty()){
			return false;
		}
		
		if(commonActions.contains(action)){
			return true;
		}
		
		HttpSession session = httpRequest.getSession();
		
		Customer customer = (Customer)session.getAttribute("customer");
		
		if(customer == null){
			return false;
		}
		
		String role = customer.getCustomerInfo().getRole();
		
		return accessMap.get(role).contains(action) || userActions.contains(action);
	}
	
	/**
	 * Split string by space. 
	 * 
	 * @param str specified String.
	 * @return list of split strings.
	 */
	private List<String> toList(String str){
		String[] arrayStrs = str.split(" ");
		
		List<String> listOfStrs = new ArrayList<>();
		
		for(String s : arrayStrs){
			listOfStrs.add(s);
		}
		
		return listOfStrs;
	}
}
