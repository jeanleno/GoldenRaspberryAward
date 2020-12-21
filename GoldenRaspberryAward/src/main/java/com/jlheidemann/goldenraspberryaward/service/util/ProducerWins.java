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
	
	public void addWindYear(int year) {
		this.winYears.add(year);
	}
	
	public int getMaxDiference() {
		if (this.winYears.size() >= 2) {
			Collections.sort(this.winYears);
			this.previousWin = this.winYears.get(0);
			this.followingWin = this.winYears.get(this.winYears.size() -1);
			if (this.followingWin > this.previousWin) {
				return this.followingWin - this.previousWin;
			}
		}
		return 0;
	}
	
	public int getMinDiference() {
		int minDifference = 0;
		int oldYear = 0;
		if (this.winYears.size() >= 2) {
			Collections.sort(this.winYears);
			for (Integer year : this.winYears) {
				if (oldYear != 0) {				
					int diff = 0;
					if (year != oldYear) {
						diff = year - oldYear;
					}
					
					if (diff < minDifference || minDifference == 0) {
						minDifference = diff;
						this.previousWin = oldYear;
						this.followingWin = year;
					}
				}
				oldYear = year;
			}
		}
		return minDifference;
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
