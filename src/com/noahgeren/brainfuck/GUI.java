package com.noahgeren.brainfuck;

import java.util.Scanner;

public class GUI {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BrainfuckInterpreter bf = new BrainfuckInterpreter(() -> {
			// Input
			return input.nextLine();
		}, (c) -> {
			// Output
			System.out.print(c);
		});
		bf.execute("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.");
		input.close();
	}

}
