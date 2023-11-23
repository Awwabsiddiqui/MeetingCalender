package com.example.springrest.method;

import java.util.ArrayList;

import com.example.springrest.entity.Ent;

public class Validation {

	public ArrayList<String> validateRegister(Ent ent) {
		ArrayList<String> err = new ArrayList<String>();
		if (ent.getName() == null || ent.getName().equals(""))
			err.add("Name is Required");
		if (ent.getPassword() == null || ent.getPassword().equals(""))
			err.add("Password is Required");

		return err;
	}

}
