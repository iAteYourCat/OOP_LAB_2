package instruments;
import java.util.Collection;
import java.util.List;

import music_instrument_interfaces.*;

public class Instrument implements InstrumentInterface{
	public final String name = "Unknown";
	public void Play()
	{
		System.out.println("Unknown instrument is playing");
	}

}
