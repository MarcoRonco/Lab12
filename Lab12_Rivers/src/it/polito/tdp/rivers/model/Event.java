package it.polito.tdp.rivers.model;


public class Event implements Comparable<Event>{
	
	public enum EventType {FLOW} ;

	private Flow f;
	private float fOut;
	
	private EventType type ;

	public Event(Flow f, float fOut, EventType type) {
		super();
		this.f = f;
		this.fOut = fOut;
		this.type = type;
	}

	public Flow getF() {
		return f;
	}

	public float getfOut() {
		return fOut;
	}

	public EventType getType() {
		return type;
	}

	@Override
	public int compareTo(Event e) {
		
		if(this.getF().getDay().getYear()-e.getF().getDay().getYear()!=0){
			
			return this.getF().getDay().getYear()-e.getF().getDay().getYear();
		
		}else {
			
			if(this.getF().getDay().getMonthValue()-e.getF().getDay().getMonthValue()!=0){
				
				return this.getF().getDay().getMonthValue()-e.getF().getDay().getMonthValue();
			
			}else {
				
				return this.getF().getDay().getDayOfMonth()-e.getF().getDay().getDayOfMonth();
			}
		}
	}
	
	
}
