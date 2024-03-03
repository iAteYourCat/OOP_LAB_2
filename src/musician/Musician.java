package musician;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.net.ssl.ExtendedSSLSession;

import music_instrument_interfaces.*;

public class Musician {
	
	public String name = "Musician";
	public List<InstrumentInterface> listOfPlayableInstruments;
	
	public Musician() 
	{
		
	}
	public Musician  (Collection<? extends InstrumentInterface> list)
	{
		this.listOfPlayableInstruments = List.copyOf(list);
	}
	/**
	 * 
	 * @param list
	 */
	public void SetNewInstrumans(Collection<? extends InstrumentInterface> list) {
		this.listOfPlayableInstruments = List.copyOf(list);
	}
	/**
	 * Checks if interfaces of instrumentToPlay cross with interfaces of listOfPlayableInstruments elements
	 * if so, then method Play() of instrumentToPlay is called
	 * @param instrumentToPlay 
	 */
	public void PlayInstrument(InstrumentInterface instrumentToPlay)
	{
		Class<?>[] instrumentToPlayInterfaces = instrumentToPlay.getClass().getInterfaces();
		for (InstrumentInterface instrument: listOfPlayableInstruments) 
		{
			Class<?>[]instrumentInterfaces = instrument.getClass().getInterfaces();
			
			for (Class<?> class1 : instrumentToPlayInterfaces){
				for (Class<?> class2 : instrumentInterfaces) {
					
					if(class1 == class2)
					{
						instrumentToPlay.Play();
						return;
					}
				}
				
			}
		}
		System.out.println(name + " cant play " + instrumentToPlay.getClass() +" instrument");
	}
}

