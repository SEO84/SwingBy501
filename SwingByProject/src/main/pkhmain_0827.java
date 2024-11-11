package main;

import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingUtilities;

import dao.pkh0827dao;
import dto.pkh0827dto;
import gui.pkh0827gui;

public class pkhmain_0827 {
	public static void main(String[] args) {
		// GUI 실행
		SwingUtilities.invokeLater(() -> new pkh0827gui());
	}
}

