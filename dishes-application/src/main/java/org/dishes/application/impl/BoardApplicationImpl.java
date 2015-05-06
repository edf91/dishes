package org.dishes.application.impl;



import java.util.List;

import javax.inject.Named;

import org.dishes.application.BoardApplication;
import org.dishes.domain.Board;

/**
 * 餐桌应用实现类
 */
@Named
public class BoardApplicationImpl implements BoardApplication{
	
	/**
	 * 添加餐桌
	 */
	public void addBoard(Board border) {
		border.save();
	}
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	public boolean isNameExists(String name) {
		return Board.isNameExists(name);
	}
	/**
	 * 餐桌列表
	 */
	public List<Board> listBoard() {
		return Board.findAllEntities();
	}
	/**
	 * 根据id获取餐桌
	 */
	public Board getBoardById(String boardId) {
		return Board.get(Board.class, boardId);
	}
	/**
	 * 根据id删除餐桌
	 */
	public void deleteBoardById(String boardId) {
		getBoardById(boardId).delete();
	}

}
