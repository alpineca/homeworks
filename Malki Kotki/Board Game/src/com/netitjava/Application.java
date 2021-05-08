//TODO: Генериране на obsticle не работи
//TODO: Нов ред при рендера не работи

package com.netitjava;

import com.netitjava.gameboard.pieces.Piece;
import com.netitjava.managers.GameManager;
import com.netitjava.util.Console;

public class Application {

	public static void main(String[] args) {

		GameManager game = new GameManager();
		game.startGame();
		
	}
}
