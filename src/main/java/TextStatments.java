import java.util.List;

/**
 * This is Text based statements generator
 * @author nasir
 *
 */
public class TextStatments implements Statement {

	private double totalAmount;
	private int frequentRenterPoints;
	Customer customer;

	public TextStatments(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String makeHeader(Customer customer) {
		return "Rental Record for " + customer.getName() + "\n";
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
		return "You owed " + totalAmount + "\n" + "You earned " + frequentRenterPoints + " frequent renter points\n";
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
