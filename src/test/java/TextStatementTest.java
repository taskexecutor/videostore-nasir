import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * This is Text based statements generator test class
 * @author nasir
 *
 */
public class TextStatementTest extends TestCase {

	private Statement statement;
	private Movie newRelease1;
	private Movie newRelease2;
	private Movie childrens;
	private Movie regular1;
	private Movie regular2;
	private Movie regular3;

	public TextStatementTest(String name) {
		super(name);
	}

	protected void setUp() {
		Customer customer = new Customer("Customer Name");
		statement = new TextStatments(customer);
		newRelease1 = new NewReleaseMovie("New Release 1");
		newRelease2 = new NewReleaseMovie("New Release 2");
		childrens = new ChildrensMovie("Childrens");
		regular1 = new RegularMovie("Regular 1");
		regular2 = new RegularMovie("Regular 2");
		regular3 = new RegularMovie("Regular 3");
	}

	private void assertAmountAndPointsForReport(double expectedAmount, int expectedPoints) {
		assertEquals(expectedAmount, statement.getAmountOwed());
		assertEquals(expectedPoints, statement.getFrequentRenterPoints());
	}

	public void testSingleNewReleaseStatement() {
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental(newRelease1, 3));
		statement.makeRentalStatement(rentals);
		assertAmountAndPointsForReport(9.0, 2);
	}
	
	public void testDualNewReleaseStatement() {
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental(newRelease1, 3));
		rentals.add(new Rental(newRelease2, 3));
		statement.makeRentalStatement(rentals);
		assertAmountAndPointsForReport(18.0, 4);
	}
	
	public void testSingleChildrensStatement() {
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental(childrens, 3));
		statement.makeRentalStatement(rentals);
		assertAmountAndPointsForReport(1.5, 1);
	}
	
	public void testMultipleRegularStatement() {
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental(regular1, 1));
		rentals.add(new Rental(regular2, 2));
		rentals.add(new Rental(regular3, 3));
		statement.makeRentalStatement(rentals);
		assertAmountAndPointsForReport(7.5, 3);
	}
	
	public void testRentalStatementFormat() {
		List<Rental> rentals = new ArrayList<>();
		rentals.add(new Rental(regular1, 1));
		rentals.add(new Rental(regular2, 2));
		rentals.add(new Rental(regular3, 3));
		assertEquals("Rental Record for Customer Name\n" + "\tRegular 1\t2.0\n" + "\tRegular 2\t2.0\n"
				+ "\tRegular 3\t3.5\n" + "You owed 7.5\n" + "You earned 3 frequent renter points\n",
				statement.makeRentalStatement(rentals));
	}
}
