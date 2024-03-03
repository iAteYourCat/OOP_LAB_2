package instruments;

import music_instrument_interfaces.Electronic_Instrument;
import music_instrument_interfaces.Stringed_Instrument;

public class ElectroGuitar implements Stringed_Instrument,Electronic_Instrument{
	public final String name = "ElectroGuitar" ;
	public void Play()
	{
	 System.out.println("ElectroGuitar is playing");
	}
}
