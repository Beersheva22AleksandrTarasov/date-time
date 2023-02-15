package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.time.BarMizvaAdjuster;
import telran.time.NextFriday13;

class DateTimeTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,dd");
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d", unit, birthDateAS, barMizvaAS,
				unit.between(birthDateAS, barMizvaAS));
		System.out.println();
	}

	@Test
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}

	@Test
	void displayCurrentDateTimeCanadaTimeZones() {

		// displaying current local date and time for all Canada time zones
		// displaying should contains time zone name

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE, d MMM yyyy, HH:mm:ss, z");

		String Vancouver = "America/Vancouver";
		ZonedDateTime dateTimeVancouver = ZonedDateTime.now(ZoneId.of(Vancouver));
		System.out.println(dateTimeVancouver.format(dtf));

		String Toronto = "America/Toronto";
		ZonedDateTime dateTimeToronto = ZonedDateTime.now(ZoneId.of(Toronto));
		System.out.println(dateTimeToronto.format(dtf));

		String Edmonton = "America/Edmonton";
		ZonedDateTime dateTimeEdmonton = ZonedDateTime.now(ZoneId.of(Edmonton));
		System.out.println(dateTimeEdmonton.format(dtf));

	}

	@Test
	void nextFriday13Test() {
		LocalDate dateResult = LocalDate.of(2023, 2, 23);
		LocalDate dateFriday = LocalDate.of(2023, 2, 17);
		while (!dateResult.getDayOfWeek().equals(dateFriday.getDayOfWeek())) {
			dateResult = dateResult.with(new NextFriday13());
		}
		System.out.printf("Next friday the 13th will be %s", dateResult);
		System.out.println();

	}

}
