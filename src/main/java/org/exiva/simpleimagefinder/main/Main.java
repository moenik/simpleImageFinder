package org.exiva.simpleimagefinder.main;

import org.exiva.simpleimagefinder.ux.JMain;

public class Main {
	public static void main(String[] args) {
		if(args.length==0) {
			System.out.println("no args -> show UI!");
			new JMain();
		}else {
			
		}
	}
}
