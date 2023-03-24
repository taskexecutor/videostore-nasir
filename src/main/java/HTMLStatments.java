import java.util.List;

/**
 * This is HTML based statement generator
 * @author nasir
 *
 */
public class HTMLStatments implements Statement {

	private double totalAmount;
	private int frequentRenterPoints;
	Customer customer;

	public HTMLStatments(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String makeHeader(Customer customer) {
		return "<head><body>"+"Rental Record for " + customer.getName() + "\n" + "</body></head>";
	}

	@Override
	public String makeRentalLines(List<Rental> rentals) {
		String rentalLines = "";

		for (Rental rental : rentals)
			rentalLines += makeRentalLine(rental);

		return rentalLines;
	}

	@Override
	public String makeRentalLine(Rental rental) {
		double thisAmount = rental.determineAmount();
		frequentRenterPoints += rental.determineFrequentRenterPoints();
		totalAmount += thisAmount;

		return formatRentalLine(rental, thisAmount);
	}

	@Override
	public String makeSummary() {
		return "<head><body>"+"You owed " + totalAmount + "\n" + "You earned " + frequentRenterPoints + " frequent renter points\n" + "</body></head>";
	}

	@Override
	public String makeRentalStatement(List<Rental> rentals) {
		clearTotals();
		return makeHeader(customer) + makeRentalLines(rentals) + makeSummary();
	}

	private void clearTotals() {
		totalAmount = 0;
		frequentRenterPoints = 0;
	}

	@Override
	public double getAmountOwed() {
		return totalAmount;
	}

	@Override
	public int getFrequentRenterPoints() {
		return frequentRenterPoints;
	}

}
