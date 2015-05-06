package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.command.CreateBoardCommand;
import org.dishes.facade.dto.BoardDTO;

/**
 * 餐桌门面层
 */
public interface BoardFacade {
	/**
	 * 添加餐桌
	 * @param command
	 * @return
	 */
	public InvokeResult<String> addBoard(CreateBoardCommand command);
	/**
	 * 餐桌列表
	 * @return
	 */
	public InvokeResult<List<BoardDTO>> listBoard();
	/**
	 * 根据id删除餐桌
	 * @param boardId
	 * @return
	 */
	public InvokeResult<String> deleteBoardById(String boardId);
	/**
	 * 根据id获取餐桌
	 * @param boardId
	 * @return
	 */
	public InvokeResult<BoardDTO> getBoardById(String boardId);
}
