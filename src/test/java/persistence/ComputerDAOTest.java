package persistence;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.ResultSet;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ConfigForTest;
import exception.ComputerNotFoundException;
import exception.InvalidDateChronology;
import model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigForTest.class)
public class ComputerDAOTest {
	
	
	@Autowired
	ComputerDAO computerDAO;

	
	ResultSet resulSetMock = mock(ResultSet.class);

	@Before
	public void setUp() throws Exception {
		

	}
	



	@Test
	public void testGetAllIntInt() {
		
		Computer computerRef = new Computer.Builder()
				.withId(23L)
				.withName("Macintosh Plus")
				.withIntroduced(LocalDate.parse("1986-01-16"))
				.withDiscontinued(LocalDate.parse("1990-10-15"))	
				.withCompanyID(1L)
				.build();


	}

	@Test
	public void testFindById() throws ComputerNotFoundException, InvalidDateChronology {

		Computer computer;
		
		computer = computerDAO.findById(23L);
		
		Computer computerRef = new Computer.Builder()
		.withId(23L)
		.withName("Macintosh Plus")
		.withIntroduced(LocalDate.parse("1986-01-16"))
		.withDiscontinued(LocalDate.parse("1990-10-15"))	
		.withCompanyID(1)
		.build();
		
		
		assertEquals(computerRef, computer);
	}


}
