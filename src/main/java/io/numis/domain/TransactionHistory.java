package io.numis.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.typeconversion.DateString;

public class TransactionHistory extends AbstractDomainNode {

	@GraphId
	private Long id;
	private int amount;
	@DateString("yyyy.MM.dd.HH.mm.ss")
	private String timeStamp;
	
	
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}


	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
