package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Flow implements Comparable<Flow>{

	private int id;
	private LocalDate day;
	private float folw;
	private River river;
	
	public Flow(int id, LocalDate day, float folw, River river) {
		this.id = id;
		this.day = day;
		this.folw = folw;
		this.river = river;
	}

	public int getId() {
		return id;
	}

	public LocalDate getDay() {
		return day;
	}

	public float getFolw() {
		return folw;
	}

	public River getRiver() {
		return river;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Flow f) {
		
		if(this.getDay().getYear()-f.getDay().getYear()!=0){
			
			return this.getDay().getYear()-f.getDay().getYear();
		
		}else {
			
			if(this.getDay().getMonthValue()-f.getDay().getMonthValue()!=0){
				
				return this.getDay().getMonthValue()-f.getDay().getMonthValue();
			
			}else {
				
				return this.getDay().getDayOfMonth()-f.getDay().getDayOfMonth();
			}
		}
	}	
}
