package com.yesid.mudanza.domain.testdatabuilder;

import java.util.ArrayList;
import java.util.List;

import com.yesid.mudanza.domain.model.Item;
import com.yesid.mudanza.domain.model.Workday;

public class WorkdaysTestDataBuilder {
	
	private List<Workday> workdays = new ArrayList<>();

	private List<Item> itemsToMoveWorkDay1;
	private List<Item> itemsToMoveWorkDay2;
	private List<Item> itemsToMoveWorkDay3;
	private List<Item> itemsToMoveWorkDay4;
	private List<Item> itemsToMoveWorkDay5;
	
	public WorkdaysTestDataBuilder withItemsToMoveWorkDay1() {
		itemsToMoveWorkDay1 = new ArrayList<>();
		itemsToMoveWorkDay1.add(new Item(30));
		itemsToMoveWorkDay1.add(new Item(30));
		itemsToMoveWorkDay1.add(new Item(1));
		itemsToMoveWorkDay1.add(new Item(1));
		Workday workday = new Workday(itemsToMoveWorkDay1);
		workdays.add(workday);
		return this;
	}
	
	public WorkdaysTestDataBuilder withItemsToMoveWorkDay2() {
		itemsToMoveWorkDay2 = new ArrayList<>();
		itemsToMoveWorkDay2.add(new Item(20));
		itemsToMoveWorkDay2.add(new Item(20));
		itemsToMoveWorkDay2.add(new Item(20));
		Workday workday = new Workday(itemsToMoveWorkDay2);
		workdays.add(workday);
		return this;
	}
	
	public WorkdaysTestDataBuilder withItemsToMoveWorkDay3() {
		itemsToMoveWorkDay3 = new ArrayList<>();
		itemsToMoveWorkDay3.add(new Item(1));
		itemsToMoveWorkDay3.add(new Item(2));
		itemsToMoveWorkDay3.add(new Item(3));
		itemsToMoveWorkDay3.add(new Item(4));
		itemsToMoveWorkDay3.add(new Item(5));
		itemsToMoveWorkDay3.add(new Item(6));
		itemsToMoveWorkDay3.add(new Item(7));
		itemsToMoveWorkDay3.add(new Item(8));
		itemsToMoveWorkDay3.add(new Item(9));
		itemsToMoveWorkDay3.add(new Item(10));
		itemsToMoveWorkDay3.add(new Item(11));
		Workday workday = new Workday(itemsToMoveWorkDay3);
		workdays.add(workday);
		return this;
	}
	
	public WorkdaysTestDataBuilder withItemsToMoveWorkDay4() {
		itemsToMoveWorkDay4 = new ArrayList<>();
		itemsToMoveWorkDay4.add(new Item(9));
		itemsToMoveWorkDay4.add(new Item(19));
		itemsToMoveWorkDay4.add(new Item(29));
		itemsToMoveWorkDay4.add(new Item(39));
		itemsToMoveWorkDay4.add(new Item(49));
		itemsToMoveWorkDay4.add(new Item(59));
		Workday workday = new Workday(itemsToMoveWorkDay4);
		workdays.add(workday);
		return this;
	}
	
	public WorkdaysTestDataBuilder withItemsToMoveWorkDay5() {
		itemsToMoveWorkDay5= new ArrayList<>();
		itemsToMoveWorkDay5.add(new Item(32));
		itemsToMoveWorkDay5.add(new Item(56));
		itemsToMoveWorkDay5.add(new Item(76));
		itemsToMoveWorkDay5.add(new Item(8));
		itemsToMoveWorkDay5.add(new Item(44));
		itemsToMoveWorkDay5.add(new Item(60));
		itemsToMoveWorkDay5.add(new Item(47));
		itemsToMoveWorkDay5.add(new Item(85));
		itemsToMoveWorkDay5.add(new Item(71));
		itemsToMoveWorkDay5.add(new Item(91));
		Workday workday = new Workday(itemsToMoveWorkDay5);
		workdays.add(workday);
		return this;
	}
	
	public List<Workday> build() {
		return workdays;
	}
}
