package org.dishes.application;

import java.util.List;

import org.dishes.domain.Board;


/**
 * 餐桌应用
 */
public interface BoardApplication {
	/**
	 * 添加餐桌
	 * @param border
	 */
	void addBoard(Board board);
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return
	 */
	boolean isNameExists(String name);
	/**
	 * 餐桌列表
	 * @return
	 */
	List<Board> listBoard();
	/**
	 * 根据id获取餐桌
	 * @param boardId
	 * @return
	 */
	Board getBoardById(String boardId);
	/**
	 * 根据id删除餐桌
	 * @param boardId
	 */
	void deleteBoardById(String boardId);
}
