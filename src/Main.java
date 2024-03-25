import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

import instruments.ElectroGuitar;
import instruments.Guitar;
import instruments.Instrument;
import music_instrument_interfaces.InstrumentInterface;
import music_instrument_interfaces.Wind_Instrument;
import musician.Musician;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static Musician musician = new Musician();
	
	

	public static void main(String[] args) {

		// Локальний клас
		class LocalClass {
			static void InteractWithUser() {
				musician.SetNewInstruments(InstrumentConst.CreateListOfInstrumance());
				
				InstrumentConst.breakPoint = false;
				while(true)
				{
					System.out.println("Choose which instruments " + musician.name + " should play\n" + "1.Instrument\n"
							+ "2.Guitar\n" + "3.ElectroGuitar\n" + "4.WindInstrument\n" + "5.Exit");
					
					try {
						int input = scanner.nextInt();
						musician.PlayInstrument( InstrumentConst.GetInstrument(input));

					} catch (Exception e) {
						System.out.println(e);
						if (!InstrumentConst.breakPoint)
							scanner.next();
					}
					if(InstrumentConst.breakPoint)break;
				}
				
			}
		}
		LocalClass.InteractWithUser();
		
		
	}
	//Внутрішній клас
	public static class InstrumentConst {
		final static public Instrument instrument = new Instrument();
		final static public Guitar guitar = new Guitar();
		final static public ElectroGuitar electroGuitar = new ElectroGuitar();
		final static public Wind_Instrument windInstrument = new Wind_Instrument() {
			
			@Override
			public void Play() {
				System.out.println("Wind instrument is playing");
				
			}
		};

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
						+ "2.Guitar\n" + "3.ElectroGuitar\n" + "4.WindInstrument\n" + "5.Exit");
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
			case 4:{
				return windInstrument;
			}
			case 5: {
				breakPoint = true;
				throw new Exception("Exit\n");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + id + '\n');
			}
		}
	}
}
