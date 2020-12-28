package com.jlheidemann.goldenraspberryaward.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jlheidemann.goldenraspberryaward.entity.Producer;

public class ProducerWins {
	private Producer producer;
	private List<Integer> winYears;
	private int previousWin;
	private int followingWin;
	
	public ProducerWins(Producer producer) {
		this.producer = producer;
		this.winYears = new ArrayList<>();
		this.previousWin = 0;
		this.followingWin = 0;
	}
	
	public void addWinYear(int year) {
		this.winYears.add(year);
	}
	
	public int getMaxDiference() {
		return getDifference(1);
	}
	
	public int getMinDiference() {
		return getDifference(0);
	}
	
	
	private int getDifference(int type) {
		int diffResult = 0;
		int oldYear = 0;
		if (this.winYears.size() >= 2) {
			Collections.sort(this.winYears);
			for (Integer year : this.winYears) {
				if (oldYear != 0) {				
					int diff = 0;
					if (year != oldYear) {
						diff = year - oldYear;
					}
					
					if ((type == 0 && diff < diffResult) || (type == 1 && diff > diffResult) || diffResult == 0) {
						diffResult = diff;
						this.previousWin = oldYear;
						this.followingWin = year;
					}
				}
				oldYear = year;
			}
		}
		return diffResult;
	}
	
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	public List<Integer> getWinYears() {
		return winYears;
	}
	public void setWinYears(List<Integer> winYears) {
		this.winYears = winYears;
	}

	public int getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}

	public int getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}
	
	

}
