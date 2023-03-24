import java.util.List;

/**
 * This is Statement interface. All common and must-have operations are listed here
 * This interface also contains default methods which are like utility stuff
 * @author nasir
 *
 */
public interface Statement {
	
	 double totalAmount = 0;
	 int frequentRenterPoints = 0;

	String makeHeader(Customer customer);

	String makeRentalLine(Rental rental);

	String makeRentalLines(List<Rental> rentals);

	String makeSummary();

	String makeRentalStatement(List<Rental> rentals);
	
	double getAmountOwed();
	
	int getFrequentRenterPoints();

	default String formatRentalLine(Rental rental, double thisAmount) {
		return "\t" + rental.getTitle() + "\t" + thisAmount + "\n";
	}

	/*default double getAmountOwed() {
	    return totalAmount;
	  }

	default int getFrequentRenterPoints() {
	    return frequentRenterPoints;
	  }*/

}
