package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.java.memoranda.ProjectImpl;
import nu.xom.Element;

public class ProjectImplTest {

	ProjectImpl project = new ProjectImpl(new Element("root_element"));
		
	@Test
	/**
	 * Test confirms public project._root elements are null
	 */
	public void TestNullAttributes() {
		assertNull(project.getDescription());
		assertNull(project.getStartDate());
		assertNull(project.getEndDate());
	}
	
	@Test
	/**
	 * Test confirms functionality of setTitle(String title)
	 */
	public void TestSetTitle() {
		
		assertNotNull(project.getTitle()); //Default title attribute is set to <> instead of null jsabbath 2/7/2018
		
		project.setTitle("project_title");
		assertEquals(project.getTitle(), "project_title");	
		assertFalse(project.getTitle().equals("wrong_title"));
	}
	
	@Test
	/**
	 * Test confirms functionality of setDescription(String description)
	 */
	public void TestSetDescription() {
		project.setDescription("a_description");
		assertEquals(project.getDescription(), "a_description");
		assertFalse(project.getDescription().equals("wront_description"));
	}
	
	public void TestGetStatus() {
		System.out.println(project.getStatus());
	}
}
