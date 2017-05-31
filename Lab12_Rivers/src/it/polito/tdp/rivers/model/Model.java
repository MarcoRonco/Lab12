package it.polito.tdp.rivers.model;

import java.util.*;

import it.polito.tdp.rivers.DAO.RiversDAO;
import it.polito.tdp.rivers.model.Event.EventType;

public class Model {
	
	private RiversDAO dao;
	private Map<Integer, River> rivers;
	public List<Flow> flow;
	public PriorityQueue<Event> queue;
	public int numGiorniInsuff = 0;
	
	public Model() {
		
		this.dao = new RiversDAO();
		this.rivers = dao.getRivers();
	}
	
	public List<Flow> getRilevamenti(River r){
		flow = dao.getFlow(r);
		return flow;
	}

	public Collection<River> getRivers() {
		return rivers.values();
	}
	
	public List<String> load(float k){
		
		queue = new PriorityQueue();
		
		float fMed = 0;
		for(Flow fl : flow){
			fMed+=fl.getFolw();
		}
		fMed=fMed/(flow.size());
		
		float q = k*fMed*30*24*3600;
		float c = q/2;
		
		float fOutMin = (float) (0.8*fMed*24*3600);
		
		double random;
		float fOut;
		Event e = null;
		for(Flow f : flow){
			
			random = Math.random();
			
			if(random>=0.95){
				fOut = fOutMin*10;
				e = new Event(f , fOut, EventType.FLOW);
				
			}
			else{
				fOut = fOutMin;
				e = new Event(f , fOut, EventType.FLOW);
			}
			
			queue.add(e);
		}
		
		return this.run(c, q);
	}
	
	public List<String> run(float c, float q){
		
		List<String> dati = new ArrayList<>();
		float media = 0;
		int i = 0;
		
		while(!queue.isEmpty()){
			Event e = queue.poll();
			i++;
			float c1 = c+e.getF().getFolw()-e.getfOut();
			
			if(c1>e.getfOut()){
				
				if(c1<q){
				 c=c1;
				}else{
				 c=q;
				}
				
			}else if(c1<e.getfOut()){
				this.numGiorniInsuff++;
				c=0;
			}else{
				c=0;
			}
			
			media+=c;
		}
		
		media=media/i;
		
		dati.add(""+numGiorniInsuff);
		dati.add(""+media);
		return dati;
	}
}
