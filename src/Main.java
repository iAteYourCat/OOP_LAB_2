import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import instruments.ElectroGuitar;
import instruments.Guitar;
import instruments.Instrument;
import music_instrument_interfaces.InstrumentInterface;
import musician.Musician;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static Musician musician = new Musician();

	public static void main(String[] args) {

		System.out.println("Choose musician name");
		String name = scanner.nextLine().strip();

		musician.name = name == "" ? musician.name : name;
		musician.SetNewInstrumans(InstrumentConst.CreateListOfInstrumance());
		InstrumentConst.breakPoint = false;
		while (true) {
			System.out.println("What instruments " + musician.name + " should play\n" + "1.Instrument\n" + "2.Guitar\n"
					+ "3.ElectroGuitar\n" + "4.end");
			try {
				int n = scanner.nextInt();
				musician.PlayInstrument(InstrumentConst.GetInstrument(n));

			} catch (Exception e) {
				System.out.println(e);
				if (!InstrumentConst.breakPoint)
					scanner.next();
			}

			if (InstrumentConst.breakPoint)
				break;
		}
		scanner.close();
	}

	public static class InstrumentConst {
		final static public Instrument instrument = new Instrument();
		final static public Guitar guitar = new Guitar();
		final static public ElectroGuitar electroGuitar = new ElectroGuitar();

		private static boolean breakPoint = false;

		/**
		 * 
		 * @return List of unique interfaces that represents types of musical
		 *         instruments
		 */
		static List<InstrumentInterface> CreateListOfInstrumance() {
			LinkedHashSet<InstrumentInterface> listToReturn = new LinkedHashSet<InstrumentInterface>();

			breakPoint = false;
			while (true) {
				System.out.println("Choose which instruments " + musician.name + " can play\n" + "1.Instrument\n"
						+ "2.Guitar\n" + "3.ElectroGuitar\n" + "4.end");
				try {
					int input = scanner.nextInt();
					listToReturn.add(GetInstrument(input));

				} catch (Exception e) {
					System.out.println(e);
					if (!breakPoint)
						scanner.next();
				}
				if (breakPoint)
					break;
			}
			return List.copyOf(listToReturn);
		}

		/**
		 * 
		 * @param id of user choice
		 * @return class with InstrumentInterface as highest interface
		 * @throws Exception
		 */
		public static InstrumentInterface GetInstrument(int id) throws Exception {

			switch (id) {
			case 1: {
				return instrument;
			}
			case 2: {
				return guitar;
			}
			case 3: {
				return electroGuitar;
			}
			case 4: {
				breakPoint = true;
				throw new Exception("Exit");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + id);
			}
		}
	}
}
