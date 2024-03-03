package instruments;

import music_instrument_interfaces.Stringed_Instrument;

public class Guitar implements Stringed_Instrument{
	public final String name = "Guitar" ;
	public void Play ()
	{
		System.out.println("Guitar is playing");
	}

}
