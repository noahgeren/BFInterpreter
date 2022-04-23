package com.noahgeren.brainfuck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BrainfuckInterpreter {
	
	private static final int TAPE_LENGTH = 30_000;
	private static final Runnable DEFAULT_OPERATOR = () -> {};
	
	private int pointer = 0, ip = 0; // memory pointer and instruction pointer
	private final int[] memory = new int[TAPE_LENGTH];
	private final Map<Character, Runnable> operatorFunctions = new HashMap<>();
	private final Stack<Integer> loopStack = new Stack<>();
	private final Deque<Character> inputQueue = new ArrayDeque<>();
	
	private String code;
	
	public BrainfuckInterpreter(final Supplier<String> input, final Consumer<Character> output) {
		operatorFunctions.put('>', () -> {
			pointer = (pointer + 1) % TAPE_LENGTH;
		});
		operatorFunctions.put('<', () -> {
			pointer = (pointer - 1 + TAPE_LENGTH) % TAPE_LENGTH;
		});
		operatorFunctions.put('+', () -> {
			memory[pointer] = (memory[pointer] + 1) % 255;
		});
		operatorFunctions.put('-', () -> {
			memory[pointer] = (memory[pointer] - 1 + 255) % 255;
		});
		operatorFunctions.put('.', () -> {
			output.accept((char) memory[pointer]);
		});
		operatorFunctions.put(',', () -> {
			if(inputQueue.isEmpty()) {
				input.get().chars().forEach(c -> inputQueue.add((char) c));
			}
			memory[pointer] = inputQueue.isEmpty() ? 0 : inputQueue.poll();
		});
		operatorFunctions.put('[', () -> {
			if(memory[pointer] !=  0) {
				loopStack.add(ip);
			} else {
				int loopCount = 0;
				for(; ip < code.length(); ip++) {
					char c = code.charAt(ip);
					if(c == '[') {
						loopCount++;
					} else if(c == ']' && --loopCount == 0) {
						break;
					}
				}
			}
		});
		operatorFunctions.put(']', () -> {
			if(loopStack.isEmpty()) {
				throw new IllegalStateException("No matching [ found.");
			}
			int loopStart = loopStack.pop() - 1;
			if(memory[pointer] !=  0) {
				ip = loopStart;
			}
		});
	}
	
	public void execute(final String code) {
		reset();
		this.code = code;
		Scanner input = new Scanner(System.in);
		char lastOperator = ' ';
		for(ip = 0; ip < code.length(); ip++) { // Instruction pointer
			char operator = code.charAt(ip);
			operatorFunctions.getOrDefault(operator, DEFAULT_OPERATOR).run();
			// Uncomment for debugging
//			if(operator != lastOperator) {
//				for(int i = 0; i < 6; i++) {
//					System.out.print(memory[i] + ", ");
//				}
//				System.out.println(" IP: " + ip);
//				try {
//					Thread.sleep(500l);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				lastOperator = operator;
//			}
		}
	}
	
	private void reset() {
		Arrays.fill(memory, 0);
		pointer = 0;
		ip = 0;
	}

}
