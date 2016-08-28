package ua.nure.jurkov.SummaryTask4.domain.course;

/**
 * Enum consist of constants for sort courses.
 * 
 * @author Eugene Jurkov
 *
 */
public enum KindOfSortCourses {
	BY_NAME_A_Z("By name(A-Z)"), 
	BY_NAME_Z_A("By name(Z-A)"), 
	BY_DURATION("By duration"), 
	BY_REGISTERED_STUDENTS("By registered students");
	
	private final String name;
	
	KindOfSortCourses(String name){
		this.name = name;
	}
	
	public static KindOfSortCourses getKindByName(String name){
		
		for(KindOfSortCourses kind : KindOfSortCourses.values()){
			if(kind.name.equals(name)){
				return kind;
			}
		}
		
		return null;
	}
}
