package io.numis.domain;

public abstract class AbstractDomainNode implements DomainNode {
	
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean equals(Object o)	{
		if (this == o)		{
			return true;
		} else if (o == null || !(o instanceof DomainNode)) {
			return false;
		} else {
			return this.compareTo((DomainNode)o)==0;
		}
	}
	
	public int compareTo(DomainNode o) {
		return ((Integer)this.getId()).compareTo((Integer)(o.getId()));
	}
}
